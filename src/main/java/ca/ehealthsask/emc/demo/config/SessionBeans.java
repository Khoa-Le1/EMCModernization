package ca.ehealthsask.emc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component
public class SessionBeans {

    @Bean("authentication")
    @Scope(WebApplicationContext.SCOPE_REQUEST)
    public Authentication authentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Bean("login")
    @Scope(WebApplicationContext.SCOPE_REQUEST)
    public LoginDetails loginDetails(){
        return new LoginDetails((LdapUserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
    }

    public static class LoginDetails{
        private String username;
        private List<String> roles;
        private String userDN;
        private String commonName;

        public LoginDetails(LdapUserDetails userDetails){
            this.roles = new ArrayList<>();
            this.username = userDetails.getUsername();
            this.userDN = userDetails.getDn();
            this.commonName = userDN.replaceAll(",*[A-BD-Z][A-Z]=(\\w+\\s*)+,*","")
                    .replace("CN=","");
            userDetails.getAuthorities().forEach((authority)->roles.add(authority.getAuthority()));
        }

        public boolean hasAnyRole(String... rls){
            for(String rl : rls)
                for(String role : roles)
                    if(role.trim().equalsIgnoreCase(rl.trim()))
                        return true;
            return false;
        }

        public  boolean hasAllRoles(String... rls){
            int matchCount = 0;
            for(String rl : rls)
                for(String role : roles)
                    if(role.trim().equalsIgnoreCase(rl.trim()))
                        matchCount++;
            return matchCount==rls.length;
        }

        public String getCommonName() {
            return commonName;
        }

        public String getUsername() {
            return username;
        }

        public List<String> getRoles() {
            return roles;
        }

        public String getUserDN() {
            return userDN;
        }
    }
}
