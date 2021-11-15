import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */

public class TesyJwt {
    public static void main(String[] args) {
        String token = createJwt();
        System.out.println("生成的令牌：" + token);
        parserJwt(token);
    }

    // 生成jwt令牌（token）
    public static String createJwt() {

        // 当前时间(毫秒数)
        long now = System.currentTimeMillis();
        // 过期时间10秒后
        long exp = now + 1000*10;

        JwtBuilder builder = Jwts.builder();
        builder.setId("1111")
                .setSubject("admin") // 主题：用户名
                .setIssuedAt(new Date()) // 签发时间
                .signWith(SignatureAlgorithm.HS256, "zxy1" )
                .setExpiration(new Date(exp)); // 过期时间

        return builder.compact(); // 开始生成jwt令牌
    }

    // 解析令牌
    public static void parserJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("zxy1")
                .parseClaimsJws(token).getBody();
        System.out.println("id: " + claims.getId());
        System.out.println("subject: " + claims.getSubject());
    }
}
