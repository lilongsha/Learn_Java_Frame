package cn.lilongsha.hibernate.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

/**
 * @Author lilongsha
 * @Description hibernate 实体拦截器
 * @Date 2021/1/29 9:34 上午
 */
public class LoggingInterceptor extends EmptyInterceptor {
    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        System.out.println("LoggingInterceptor onFlushDirty: " + entity.toString());
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }
}
