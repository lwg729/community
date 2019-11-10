package life.lwg.community.controller;

import life.lwg.community.dto.AccessTokenDTO;
import life.lwg.community.dto.GithubUser;
import life.lwg.community.provider.GithubPorvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubPorvider githubPorvider;
    @GetMapping("/callback")
    @ResponseBody
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("f30ffb702332e2e9ac57");
        accessTokenDTO.setClient_secret("c0c3b7967f8c04a139708130c7bf0d958794a54d");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8081/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubPorvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubPorvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
