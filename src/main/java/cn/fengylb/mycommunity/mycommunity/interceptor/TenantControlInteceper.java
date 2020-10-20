package cn.fengylb.mycommunity.mycommunity.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TenantControlInteceper implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            if("login".equals(invocation.getMethod().getName())) {
                return invocation.proceed();
            }

            System.out.println("控制器层面，，计算 tenant。。。");
            String tenant = "communitydb2";
            TenantContextHolder.setTenant(tenant);
            return invocation.proceed();
        }finally {
            TenantContextHolder.remove();
            System.out.println("控制器层面，，移除tenant。。。");
        }

    }
}
