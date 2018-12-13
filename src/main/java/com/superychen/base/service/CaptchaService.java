package com.superychen.base.service;

import com.superychen.base.common.error.exception.CaptchaExpiredException;
import com.superychen.base.common.error.exception.IncorrectCaptchaException;
import com.superychen.base.common.util.captcha.Captcha;
import com.superychen.base.common.util.captcha.SimpleCaptchaService;
import com.superychen.base.common.util.captcha.filter.predefined.RippleFilterFactory;
import com.superychen.base.controller.model.CaptchaModel;
import com.superychen.base.controller.model.CaptchaResp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

/**
 * 图片验证码
 */
@Slf4j
@Service
public class CaptchaService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String CAPTCHA_PREFIX = "captcha:";

    private SimpleCaptchaService simpleCaptchaService =
            new SimpleCaptchaService(180, 100, Color.DARK_GRAY, Color.WHITE, 6, new RippleFilterFactory());

    private String captchaKey(String cid) {
        return CAPTCHA_PREFIX + cid;
    }

    public CaptchaModel getCaptcha() {
        try {
            Captcha captcha = simpleCaptchaService.getCaptcha();
            String challenge = captcha.getChallenge();
            BufferedImage image = captcha.getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
            ImageIO.write(image, "png", baos);//写入流中
            byte[] bytes = baos.toByteArray();//转换成字节
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = "data:image/jpeg;base64," + encoder.encodeBuffer(bytes).trim()
                    .replaceAll("\n", "")
                    .replaceAll("\r", "");
            return new CaptchaModel(challenge, base64);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public CaptchaResp generateCaptcha() {
        String cid = UUID.randomUUID().toString();
        log.info("generateCaptcha {}", cid);
        CaptchaModel captcha = getCaptcha();
        String word = captcha.getWord();
        String base64 = captcha.getCaptcha();
        //存入redis，有效期5分钟
        stringRedisTemplate.opsForValue().set(captchaKey(cid), word, 5, TimeUnit.MINUTES);
        log.info("generateCaptcha {} code {}", cid, word);
        return new CaptchaResp(cid, base64);
    }

    public void checkCaptcha(String cid, String word) {
        log.info("checkCaptcha {} word={}", cid, word);
        String code = stringRedisTemplate.opsForValue().get(captchaKey(cid));
        log.info("checkCaptcha {} code={}", cid, code);
        if (StringUtils.isBlank(code)) {
            log.info("checkCaptcha {} expired", cid);
            throw new CaptchaExpiredException();
        }
        if (!word.equals(code)) {
            log.info("checkCaptcha {} incorrect", cid);
            throw new IncorrectCaptchaException();
        }
        stringRedisTemplate.delete(captchaKey(cid));
        log.info("checkCaptcha {} success", cid);
    }

}