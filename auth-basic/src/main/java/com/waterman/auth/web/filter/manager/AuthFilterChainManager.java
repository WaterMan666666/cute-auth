package com.waterman.auth.web.filter.manager;

import com.waterman.auth.web.FilterChainResolver;
import com.waterman.auth.web.PatternMatcher;
import com.waterman.auth.web.utils.AntPathMatcher;
import com.waterman.auth.web.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
public class AuthFilterChainManager implements FilterChainResolver {

    private static Logger log = LoggerFactory.getLogger(AuthFilterChainManager.class);

    private  List<String> filters;

    private  Map<String, Filter> filterMap;
    /**
     * key=过滤器名称 ， value= 过滤器对应的路径
     */
    private  Map<String, List<String>> filterPaths;

    private static final String DEFAULT_PATH_SEPARATOR = "/";

    private PatternMatcher pathMatcher;


    public AuthFilterChainManager(Map<String, Filter> filterMap, Map<String, List<String>> filterPaths) {
        this.filterMap = new HashMap<>();
        if(filterMap != null){
            this.filterMap.putAll(filterMap);
        }
        this.filterPaths = new HashMap<>();
        if(filterPaths != null){
            this.filterPaths.putAll(filterPaths);
        }
        this.pathMatcher = new AntPathMatcher();
        this.filters = this.filterMap.entrySet().stream()
              //  .sorted(Comparator.comparingInt(item -> item.getValue().getOrder()))
                .map(item -> item.getKey()).collect(Collectors.toList());
        for (int i = 0; i < filters.size(); i++) {
            if(log.isInfoEnabled()){
                String name = filters.get(i);
                log.info("regist filter named: {}, class: {}", name, filterMap.get(name).getClass());
            }
        }
    }

    @Override
    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originChain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = WebUtils.getPathWithinApplication(httpServletRequest);

        // "/resource/menus" 这两种情况应该等价 "resource/menus/"
        if (requestURI != null && !DEFAULT_PATH_SEPARATOR.equals(requestURI)
                && requestURI.endsWith(DEFAULT_PATH_SEPARATOR)) {
            requestURI = requestURI.substring(0, requestURI.length() - 1);
        }
        final String uri = requestURI;
        List<Filter> filterList = filters.stream().filter(filterName -> {
            //过滤掉不符合path要求的过滤器
            return filterPaths.entrySet().stream().anyMatch(item ->
                    pathMatches(item.getKey(), uri) && item.getValue().contains(filterName)); })
            //获取每个过滤器对应的名称
            .map(filterName -> filterMap.get(filterName))
            .collect(Collectors.toList());

        return  new AuthFilterChain(filterList, originChain);
    }

    protected boolean pathMatches(String pattern, String path) {
        PatternMatcher pathMatcher = getPathMatcher();
        return pathMatcher.matches(pattern, path);
    }

    public PatternMatcher getPathMatcher() {
        return pathMatcher;
    }
}
