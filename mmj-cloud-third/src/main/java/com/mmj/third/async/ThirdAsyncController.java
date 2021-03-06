package com.mmj.third.async;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmj.common.controller.BaseController;

/**
 * 本控制器为mmj-cloud-third下共用，提供不需要进行令牌认证的方法给消息服务以及定时任务调度进行调用
 * @author shenfuding
 *
 */
@Slf4j
@RestController
@RequestMapping("/async")
@Api(value = "第三方异步处理控制器")
public class ThirdAsyncController extends BaseController {

}
