package com.qi.assistant.web.tool;

import com.qi.common.model.vo.paging.HeaderVO;
import com.qi.common.model.vo.paging.TitleVO;
import com.qi.common.util.ThrowableUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class Deploy
 *
 * @author 张麒 2016/6/27.
 * @version Description:
 */
public class Header {

    private Header() {
    }

    private static Header deploy = null;

    public static Header getInstance() {
        if (null == deploy)
            synchronized (Header.class) {
                if (null == deploy) deploy = new Header();
            }
        return deploy;
    }

    public HeaderVO buildHeader(String type) {
        HeaderVO header = new HeaderVO();
        Map<String, TitleVO> map = new LinkedHashMap<>();
        switch (type) {
            // 燃气设置页面
            case "Gas":
                // 显示全选Checkbox
                header.setCheckbox(true);
                // 标题9列
                map.put("billdate", new TitleVO("billdate", "单据日期", "单据日期", true, 120));
                map.put("employe", new TitleVO("employe", "抄表员", "抄表员", 120));
                map.put("grade", new TitleVO("grade", "用煤阶梯", "用煤阶梯", 100));
                map.put("quantity", new TitleVO("quantity", "抄表数", "抄表数", 100));
                map.put("gastotal", new TitleVO("gastotal", "消费量", "消费量", true, 100));
                map.put("gasprice", new TitleVO("gasprice", "单价", "单价", 100));
                map.put("total", new TitleVO("total", "实付燃气费", "实付燃气费", true, 100));
                map.put("isreset", new TitleVO("isreset", "是否封账", "是否封账", 100, TitleVO.fieldType.multiNode));
                map.put("createtime", new TitleVO("createtime", "创建时间", "创建时间", 150, TitleVO.fieldType.dateDash));
                map.put(TitleVO.fieldType.operate.name(), new TitleVO(TitleVO.fieldType.operate.name(), "", "", TitleVO.fieldType.operate));
                break;
            // 水费设置页面
            case "Water":
                // 显示全选Checkbox
                header.setCheckbox(true);
                // 标题10列
                map.put("billdate", new TitleVO("billdate", "单据日期", "单据日期", true, 120));
                map.put("employe", new TitleVO("employe", "抄表员", "抄表员", 120));
                map.put("grade", new TitleVO("grade", "用水阶梯", "用水阶梯", 100));
                map.put("quantity", new TitleVO("quantity", "抄表数", "抄表数", 100));
                map.put("watertotal", new TitleVO("watertotal", "消费量", "消费量", true, 100));
                map.put("wateramount", new TitleVO("wateramount", "用水金额", "用水金额", 100));
                map.put("drainageamount", new TitleVO("drainageamount", "排水金额", "排水金额", 100));
                map.put("total", new TitleVO("total", "实付水费", "实付水费", true, 100));
                map.put("isreset", new TitleVO("isreset", "是否封账", "是否封账", 100, TitleVO.fieldType.multiNode));
                map.put("createtime", new TitleVO("createtime", "创建时间", "创建时间", 150, TitleVO.fieldType.dateDash));
                map.put(TitleVO.fieldType.operate.name(), new TitleVO(TitleVO.fieldType.operate.name(), "", "", TitleVO.fieldType.operate));
                break;
            // 电费设置页面
            case "Electric":
                // 显示全选Checkbox
                header.setCheckbox(true);
                // 标题11列
                map.put("billdate", new TitleVO("billdate", "单据日期", "单据日期", true, 120));
                map.put("employe", new TitleVO("employe", "抄表员", "抄表员", 120));
                map.put("grade", new TitleVO("grade", "用电阶梯", "用电阶梯", 100));
                map.put("peakquantity", new TitleVO("peakquantity", "抄表数(峰)", "抄表数(峰)", 100));
                map.put("flatquantity", new TitleVO("flatquantity", "抄表数(谷)", "抄表数(谷)", 100));
                map.put("peaktotal", new TitleVO("peaktotal", "消费量(峰)", "消费量(峰)", true, 100));
                map.put("flattotal", new TitleVO("flattotal", "消费量(谷)", "消费量(谷)", true, 100));
                map.put("totalshould", new TitleVO("totalshould", "应付电费", "应付电费", true, 100));
                map.put("totalactual", new TitleVO("totalactual", "实付电费", "实付电费", true, 100));
                map.put("isreset", new TitleVO("是否封账", "是否封账", "是否封账", 100, TitleVO.fieldType.multiNode));
                map.put("createtime", new TitleVO("createtime", "创建时间", "创建时间", 150, TitleVO.fieldType.dateDash));
                map.put(TitleVO.fieldType.operate.name(), new TitleVO(TitleVO.fieldType.operate.name(), "", "", TitleVO.fieldType.operate));
                break;
            default:
                ThrowableUtil.throwRuntimeException("Can't find the matching menuIndex");
        }
        header.setTitle(map);
        return header;
    }

    public HeaderVO buildOptions(String type) {
        HeaderVO header = new HeaderVO();
        Map<String, TitleVO> map = new LinkedHashMap<>();
        switch (type) {
            // 燃气设置页面
            case "Gas":
                // 显示全选Checkbox
                header.setCheckbox(true);
                // 标题7列
                map.put("intervaltype", new TitleVO("intervaltype", "抄表日期间隔类型", "抄表日期间隔类型", TitleVO.fieldType.multiNode));
                map.put("intervaldate", new TitleVO("intervaldate", "抄表日期间隔时间", "抄表日期间隔时间"));
                map.put("employe", new TitleVO("employe", "抄表员", "抄表员"));
                map.put("grade", new TitleVO("grade", "用量级别", "用量级别", true, 100));
                map.put("quantity", new TitleVO("quantity", "可用量", "可用量", true, 100));
                map.put("amend", new TitleVO("amend", "可用量修正", "可用量修正", true, 100));
                map.put("priceone", new TitleVO("priceone", "燃气费单价", "燃气费单价", true, 100));
                map.put(TitleVO.fieldType.operate.name(), new TitleVO("", "", "", TitleVO.fieldType.operate));
                break;
            // 水费设置页面
            case "Water":
                // 显示全选Checkbox
                header.setCheckbox(true);
                // 标题9列
                map.put("intervaltype", new TitleVO("intervaltype", "抄表日期间隔类型", "抄表日期间隔类型", TitleVO.fieldType.multiNode));
                map.put("intervaldate", new TitleVO("intervaldate", "抄表日期间隔时间", "抄表日期间隔时间"));
                map.put("employe", new TitleVO("employe", "抄表员", "抄表员"));
                map.put("grade", new TitleVO("grade", "用量级别", "用量级别", true, 100));
                map.put("quantity", new TitleVO("quantity", "可用量", "可用量", true, 100));
                map.put("amend", new TitleVO("amend", "可用量修正", "可用量修正", true, 100));
                map.put("priceone", new TitleVO("priceone", "水费单价", "水费单价", true, 100));
                map.put("pricetwo", new TitleVO("pricetwo", "污水单价", "污水单价", true, 100));
                map.put("formula", new TitleVO("formula", "排水计算(%)", "排水计算(%)", true, 100));
                map.put(TitleVO.fieldType.operate.name(), new TitleVO("", "", "", TitleVO.fieldType.operate));
                break;
            // 电费设置页面
            case "Electric":
                // 显示全选Checkbox
                header.setCheckbox(true);
                // 标题8列
                map.put("intervaltype", new TitleVO("intervaltype", "抄表日期间隔类型", "抄表日期间隔类型", TitleVO.fieldType.multiNode));
                map.put("intervaldate", new TitleVO("intervaldate", "抄表日期间隔时间", "抄表日期间隔时间"));
                map.put("employe", new TitleVO("employe", "抄表员", "抄表员"));
                map.put("grade", new TitleVO("grade", "用量级别", "用量级别", true, 100));
                map.put("quantity", new TitleVO("quantity", "可用量", "可用量", true, 100));
                map.put("amend", new TitleVO("amend", "可用量修正", "可用量修正", true, 100));
                map.put("priceone", new TitleVO("priceone", "电费单价(峰)", "电费单价(峰)", true, 100));
                map.put("pricetwo", new TitleVO("pricetwo", "电费单价(谷)", "电费单价(谷)", true, 100));
                map.put(TitleVO.fieldType.operate.name(), new TitleVO("", "", "", TitleVO.fieldType.operate));
                break;
            default:
                ThrowableUtil.throwRuntimeException("Can't find the matching menuIndex");
        }
        header.setTitle(map);
        return header;
    }
}
