package com.blinkfox.fenix.config;

import static com.blinkfox.fenix.consts.SymbolConst.*;

import com.blinkfox.fenix.config.entity.NormalConfig;
import com.blinkfox.fenix.config.entity.TagHandler;
import com.blinkfox.fenix.config.entity.XmlContext;
import com.blinkfox.fenix.core.FenixHandler;
import com.blinkfox.fenix.core.concrete.BetweenHandler;
import com.blinkfox.fenix.core.concrete.ChooseHandler;
import com.blinkfox.fenix.core.concrete.EndsWithHandler;
import com.blinkfox.fenix.core.concrete.ImportHandler;
import com.blinkfox.fenix.core.concrete.InHandler;
import com.blinkfox.fenix.core.concrete.IsNullHandler;
import com.blinkfox.fenix.core.concrete.LikeHandler;
import com.blinkfox.fenix.core.concrete.NormalHandler;
import com.blinkfox.fenix.core.concrete.SetHandler;
import com.blinkfox.fenix.core.concrete.StartsWithHandler;
import com.blinkfox.fenix.core.concrete.TextHandler;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import org.dom4j.Node;

/**
 * Fenix 的默认主配置类.
 *
 * @author blinkfox on 2019-08-04.
 * @see FenixConfigManager
 * @see TagHandler
 */
public class FenixConfig {

    /**
     * 所有 Fenix XML 文档的缓存 map.
     *
     * <p>该 Map 的 key 是资源的路径（将 XML 命名空间和 fenixId 用"."号分割），value 是 dom4j 的文档节点 Node.</p>
     */
    @Getter
    private static final Map<String, Node> fenixs = new HashMap<>();

    /**
     * 初始化默认的一些标签和 TagHandler 实例到 HashMap 集合中，key 是标签字符串,value 是 TagHandler 实例.
     */
    @Getter
    private static final Map<String, TagHandler> tagHandlerMap = new HashMap<>(128);
    
    /* ------- 添加默认的标签和对应的 TagHandler 处理器，如：普通条件, 'like', 'between', 'in' 等. ------- */

    static {
        // “等于”的相关标签：equal、andEqual、orEqual.
        add("equal", NormalHandler.class, EQUAL);
        add("andEqual", AND, NormalHandler.class, EQUAL);
        add("orEqual", OR, NormalHandler.class, EQUAL);

        // “不等于”的相关标签：notEqual、andNotEqual、orNotEqual.
        add("notEqual", NormalHandler.class, NOT_EQUAL);
        add("andNotEqual", AND, NormalHandler.class, NOT_EQUAL);
        add("orNotEqual", OR, NormalHandler.class, NOT_EQUAL);

        // “大于”的相关标签：greaterThan、andGreaterThan、orGreaterThan.
        add("greaterThan", NormalHandler.class, GT);
        add("andGreaterThan", AND, NormalHandler.class, GT);
        add("orGreaterThan", OR, NormalHandler.class, GT);

        // “小于”的相关标签：lessThan、andLessThan、orLessThan.
        add("lessThan", NormalHandler.class, LT);
        add("andLessThan", AND, NormalHandler.class, LT);
        add("orLessThan", OR, NormalHandler.class, LT);

        // “大于等于”的相关标签：greaterThanEqual、andGreaterThanEqual、orGreaterThanEqual.
        add("greaterThanEqual", NormalHandler.class, GTE);
        add("andGreaterThanEqual", AND, NormalHandler.class, GTE);
        add("orGreaterThanEqual", OR, NormalHandler.class, GTE);

        // “小于等于”的相关标签：lessThanEqual、andLessThanEqual、orLessThanEqual.
        add("lessThanEqual", NormalHandler.class, LTE);
        add("andLessThanEqual", AND, NormalHandler.class, LTE);
        add("orLessThanEqual", OR, NormalHandler.class, LTE);

        // "LIKE" 的相关标签：like、andLike、orLike.
        add("like", LikeHandler.class, LIKE);
        add("andLike", AND, LikeHandler.class, LIKE);
        add("orLike", OR, LikeHandler.class, LIKE);

        // "NOT LIKE" 的相关标签：notLike、andNotLike、orNotLike.
        add("notLike", LikeHandler.class, NOT_LIKE);
        add("andNotLike", AND, LikeHandler.class, NOT_LIKE);
        add("orNotLike", OR, LikeHandler.class, NOT_LIKE);

        // "LIKE 前缀匹配" 的相关标签：startsWith、andStartsWith、orStartsWith.
        add("startsWith", StartsWithHandler.class, LIKE);
        add("andStartsWith", AND, StartsWithHandler.class, LIKE);
        add("orStartsWith", OR, StartsWithHandler.class, LIKE);

        // "NOT LIKE 前缀匹配" 的相关标签：notStartsWith、andNotStartsWith、orNotStartsWith.
        add("notStartsWith", StartsWithHandler.class, NOT_LIKE);
        add("andNotStartsWith", AND, StartsWithHandler.class, NOT_LIKE);
        add("orNotStartsWith", OR, StartsWithHandler.class, NOT_LIKE);

        // "LIKE 后缀匹配" 的相关标签：endsWith、andEndsWith、orEndsWith.
        add("endsWith", EndsWithHandler.class, LIKE);
        add("andEndsWith", AND, EndsWithHandler.class, LIKE);
        add("orEndsWith", OR, EndsWithHandler.class, LIKE);

        // "NOT LIKE 后缀匹配" 的相关标签：notEndsWith、andNotEndsWith、orNotEndsWith.
        add("notEndsWith", EndsWithHandler.class, NOT_LIKE);
        add("andNotEndsWith", AND, EndsWithHandler.class, NOT_LIKE);
        add("orNotEndsWith", OR, EndsWithHandler.class, NOT_LIKE);

        // "BETWEEN" 的相关标签：between、andBetween、orBetween.
        add("between", BetweenHandler.class);
        add("andBetween", AND, BetweenHandler.class);
        add("orBetween", OR, BetweenHandler.class);

        // "IN" 的相关标签：in、andIn、orIn.
        add("in", InHandler.class, IN);
        add("andIn", AND, InHandler.class, IN);
        add("orIn", OR, InHandler.class, IN);

        // "NOT IN" 的相关标签：notIn、andNotIn、orNotIn.
        add("notIn", InHandler.class, NOT_IN);
        add("andNotIn", AND, InHandler.class, NOT_IN);
        add("orNotIn", OR, InHandler.class, NOT_IN);

        // "IS NULL" 的相关标签：isNull、andIsNull、orIsNull.
        add("isNull", IsNullHandler.class, IS_NULL);
        add("andIsNull", AND, IsNullHandler.class, IS_NULL);
        add("orIsNull", OR, IsNullHandler.class, IS_NULL);

        // "IS NOT NULL" 的相关标签：isNotNull、andIsNotNull、orIsNotNull.
        add("isNotNull", IsNullHandler.class, IS_NOT_NULL);
        add("andIsNotNull", AND, IsNullHandler.class, IS_NOT_NULL);
        add("orIsNotNull", OR, IsNullHandler.class, IS_NOT_NULL);

        // 其他标签：text、import、choose.
        add("text", TextHandler.class);
        add("import", ImportHandler.class);
        add("choose", ChooseHandler.class);
        add("set", SetHandler.class);
    }

    /**
     * 配置 Fenix 的普通配置信息(默认配置方法，开发者可覆盖此方法来做一些自定义配置).
     *
     * @param normalConfig 常规配置实例
     */
    public void configNormal(NormalConfig normalConfig) {
        normalConfig.setDebug(false).setPrintBanner(true).setPrintSqlInfo(true);
    }

    /**
     * 配置 XML 文件的标识和资源路径.
     *
     * @param ctx xmlContext 实例
     */
    public void configXml(XmlContext ctx) {
        // 子类可重写此方法，来增加新的 XML 及命名空间的配置.
    }

    /**
     * 配置标签和其对应的处理类(默认了许多常用的标签，开发者可覆盖此方法来配置更多的自定义标签).
     */
    public void configTagHandler() {
        // 子类可重写此方法，来增加新的 SQL 标签和该标签对应的处理器.
    }

    /**
     * 添加自定义标签和该 SQL 片段对应的 {@link TagHandler} 处理器实现的 class.
     *
     * @param tagName 标签名称
     * @param handlerCls 标签处理器类的 Class
     */
    protected static void add(String tagName, Class<? extends FenixHandler> handlerCls) {
        tagHandlerMap.put(tagName, new TagHandler(handlerCls));
    }

    /**
     * 添加自定义标签、SQL 片段前缀和该 SQL 片段对应的 {@link TagHandler} 处理器实现的 class.
     *
     * @param tagName 标签名称
     * @param prefix SQL 片段前缀
     * @param handlerCls 标签处理器类的 Class
     */
    protected static void add(String tagName, String prefix, Class<? extends FenixHandler> handlerCls) {
        tagHandlerMap.put(tagName, new TagHandler(prefix, handlerCls));
    }

    /**
     * 添加自定义标签、SQL 操作符和该 SQL 片段对应的 {@link TagHandler} 处理器实现的 class.
     *
     * @param tagName 标签名称
     * @param handlerCls 标签处理器类的 Class
     * @param symbol SQL 操作符
     */
    protected static void add(String tagName, Class<? extends FenixHandler> handlerCls, String symbol) {
        tagHandlerMap.put(tagName, new TagHandler(handlerCls, symbol));
    }

    /**
     * 添加自定义标签、SQL 片段前缀、SQL 操作符和该 SQL 片段对应的 {@link TagHandler} 处理器实现的 class.
     *
     * @param tagName 标签名称
     * @param prefix 前缀
     * @param handlerCls 标签处理器类的 Class
     * @param symbol SQL 操作符
     */
    protected static void add(String tagName, String prefix,
            Class<? extends FenixHandler> handlerCls, String symbol) {
        tagHandlerMap.put(tagName, new TagHandler(prefix, handlerCls, symbol));
    }

}