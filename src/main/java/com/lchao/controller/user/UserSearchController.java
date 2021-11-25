package com.lchao.controller.user;

import com.lchao.common.Result;
import com.lchao.service.IAllTicketService;
import com.lchao.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user/search")
public class UserSearchController {

    @Autowired
    private ISiteService iSiteService;
    @Autowired
    private IAllTicketService iAllTicketService;

    @GetMapping("/getSiteList")
    public Result getSiteList() {
        return iSiteService.getSiteList();
    }

    @GetMapping("/moreTicket")
    public Result moreTicket(Integer startId, Integer endId, String startTime, @RequestParam(required = false) String types) {
        return iAllTicketService.searchTicket(startId, endId, startTime, types);
    }
}
