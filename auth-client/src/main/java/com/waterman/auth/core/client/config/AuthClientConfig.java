package com.waterman.auth.core.client.config;

import com.waterman.auth.basic.enums.LoginScop;
import com.waterman.auth.core.SessionDao;
import com.waterman.auth.core.client.enums.SessionModel;
import com.waterman.auth.web.filter.AnonymousFilter;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
public class AuthClientConfig {

    public AuthClientConfig() {
        filters = new HashMap<>();
        filters.put("anno", new AnonymousFilter());
        filterMap = new HashMap<>();
    }

    private Map<String, Filter> filters;

    private Map<String, List<String>> filterMap;

    private SessionDao sessionDao;

    private LoginScop scop;

    private SessionModel model;

    private int sessionExpireTime;

    public void addFilter(String name, Filter filter){
        filters.put(name, filter);
    }
    public void addFilterMap(String path, List<String> filterNames){
        filterMap.put(path, filterNames);
    }

    public SessionDao getSessionDao() {
        return sessionDao;
    }

    public void setSessionDao(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    public LoginScop getScop() {
        return scop;
    }

    public void setScop(LoginScop scop) {
        this.scop = scop;
    }

    public SessionModel getModel() {
        return model;
    }

    public void setModel(SessionModel model) {
        this.model = model;
    }

    public int getSessionExpireTime() {
        return sessionExpireTime;
    }

    public void setSessionExpireTime(int sessionExpireTime) {
        this.sessionExpireTime = sessionExpireTime;
    }

    public Map<String, Filter> getFilters() {
        return filters;
    }

    public Map<String, List<String>> getFilterMap() {
        return filterMap;
    }
}
