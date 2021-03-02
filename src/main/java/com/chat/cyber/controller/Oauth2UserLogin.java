package com.chat.cyber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Oauth2UserLogin {

    @GetMapping(value = "/api/login")
    public @ResponseBody
    String loginPage() {
        String data = "<p>User login page</p>\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <td><a href=\"oauth2/authorization/keycloak\">Log in (Keycloak)</a></td>\n" +
                "        </tr>\n" +
                "            <tr>\n" +
                "               <td><br>" +
                "                   <form action=\"api/oauth2/logout\">" +
                "                       <input type=\"submit\" value=\"Log out\" />" +
                "                   </form>" +
                "               </td>\n" +
                "            </tr>\n" +
                "    </table>";

        return data;
    }

}
