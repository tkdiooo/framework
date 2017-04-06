package com.qi.assistant.web.tool;

import com.qi.assistant.web.model.vo.BarVO;
import com.qi.assistant.web.model.vo.PieVO;
import com.qi.common.constants.EchartsConstants.Boolean;
import com.qi.common.constants.EchartsConstants.HorizAlign;
import com.qi.common.constants.EchartsConstants.Type;
import com.qi.common.model.vo.echarts.bar.OptionBar;
import com.qi.common.model.vo.echarts.bar.SeriesBar;
import com.qi.common.model.vo.echarts.bar.series.ItemStyle;
import com.qi.common.model.vo.echarts.bar.series.MarkPoint;
import com.qi.common.model.vo.echarts.common.*;
import com.qi.common.model.vo.echarts.common.Legend.Orient;
import com.qi.common.model.vo.echarts.common.Tooltip.Trigger;
import com.qi.common.model.vo.echarts.common.areastyle.AreaStyle;
import com.qi.common.model.vo.echarts.common.axis.AxisLabel;
import com.qi.common.model.vo.echarts.common.label.Label;
import com.qi.common.model.vo.echarts.common.label.Label.Position;
import com.qi.common.model.vo.echarts.common.label.Normal;
import com.qi.common.model.vo.echarts.common.tooltip.AxisPointer;
import com.qi.common.model.vo.echarts.common.visualmap.Pieces;
import com.qi.common.model.vo.echarts.common.visualmap.VisualMap;
import com.qi.common.model.vo.echarts.line.OptionLine;
import com.qi.common.model.vo.echarts.line.SeriesLine;
import com.qi.common.model.vo.echarts.line.series.DataMA;
import com.qi.common.model.vo.echarts.line.series.MarkArea;
import com.qi.common.model.vo.echarts.pie.OptionPie;
import com.qi.common.model.vo.echarts.pie.SeriesPie;
import com.qi.common.model.vo.echarts.pie.series.DataPie;
import com.qi.common.util.ListUtil;
import com.qi.common.util.MapUtil;
import com.qi.common.util.StringUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Class Echarts
 *
 * @author 张麒 2016/9/9.
 * @version Description:
 */
public class Echarts {

    public static OptionPie buildPieEcharts(List<PieVO> pie, String year) {
        Normal normal = new Normal();
        normal.setFormatter("{c}");
        normal.setPosition(Position.inner);
        Label label = new Label(normal);

        SeriesPie series = new SeriesPie();
        series.setName("水/电/燃气费用");
        series.setType(Type.pie);
        series.setRadius(new String[]{"0", "55%"});
        series.setCenter(new String[]{"50%", "55%"});
        series.setLabel(label);

        List<DataPie> data = ListUtil.getInstance();
        BigDecimal total = new BigDecimal(0);
        for (PieVO aPie : pie) {
            BigDecimal amount = aPie.getAmount();
            data.add(new DataPie(aPie.getTitle(), amount));
            total = total.add(amount);
        }
        series.setData(data);

        Title title = new Title();
        title.setText("费用分析");
        title.setSubtext(StringUtil.isNotBlank(year) ? year + "年度：" + total + "(RMB)" : "历年开销(" + total + "RMB)");
        title.setX(HorizAlign.center);

        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger(Trigger.item);
        tooltip.setFormatter("{a} <br/>{b}: {c} ({d}%)");

        Legend legend = new Legend();
        legend.setOrient(Orient.vertical);
        legend.setX(HorizAlign.left);
        legend.setData(new String[]{"水费", "电费", "燃气费"});


        OptionPie option = new OptionPie();
        option.setTitle(title);
        option.setTooltip(tooltip);
        option.setLegend(legend);
        option.getSeries().add(series);

        return option;
    }

    public static OptionBar buildBarEcharts(List<BarVO> bar, String year) {
        OptionBar option = new OptionBar();

        SeriesBar series;
        Map<String, SeriesBar> seriesMap = MapUtil.getInstance();

        String[] years = new String[bar.size()];

        for (int i = 0; i < bar.size(); i++) {
            years[i] = bar.get(i).getYear() + "年";
            String[] titles = bar.get(i).getTitle().split(",");
            String[] amounts = bar.get(i).getAmount().split(",");
            for (int k = 0; k < titles.length; k++) {
                if (!seriesMap.containsKey(titles[k])) {
                    series = new SeriesBar();
                    series.setName(titles[k]);
                    series.setType(Type.bar);
                    series.setLabel(new Label(new Normal()));
                    series.getLabel().getNormal().setShow(Boolean.TRUE.getValue());
                    series.getLabel().getNormal().setPosition(Position.inside);
                    series.getLabel().getNormal().setFormatter("{a}\n{c}");
                    series.setData(new String[bar.size()]);
                    series.setMarkPoint(new MarkPoint());
                    series.setItemStyle(new ItemStyle());
                    seriesMap.put(titles[k], series);
                }
                seriesMap.get(titles[k]).getData()[i] = amounts[k];
            }
        }

        option.getSeries().add(seriesMap.get("总金额"));
        option.getSeries().add(seriesMap.get("水费"));
        option.getSeries().add(seriesMap.get("电费"));
        option.getSeries().add(seriesMap.get("燃气费"));


        Title title = new Title();
        title.setText("费用同比");
        title.setSubtext((Integer.valueOf(year) - 1) + " ~ " + year + "年");
        title.setX(HorizAlign.center);
        option.setTitle(title);

        AxisPointer axisPointer = new AxisPointer();
        axisPointer.setType(AxisPointer.Type.shadow);
        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger(Trigger.axis);
        tooltip.setAxisPointer(axisPointer);
        option.setTooltip(tooltip);

        Legend legend = new Legend();
        legend.setOrient(Orient.vertical);
        legend.setX(HorizAlign.right);
        legend.setData(new String[]{"总金额", "水费", "电费", "燃气费"});
        option.setLegend(legend);

        XAxis xAxis = new XAxis();
        xAxis.setType(XAxis.Type.category);
        xAxis.setData(years);
        option.setxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setType(XAxis.Type.value);
        option.setyAxis(yAxis);

        option.setCalculable(Boolean.TRUE.getValue());

        return option;
    }

    public static OptionLine buildLineEcharts(List<BarVO> bar, String year) {
        OptionLine option = new OptionLine();

        SeriesLine series;
        Map<String, SeriesLine> seriesMap = MapUtil.getInstance();

        String[] month = new String[bar.size()];
        for (int i = 0; i < bar.size(); i++) {
            month[i] = bar.get(i).getYear() + "月";
            String[] titles = bar.get(i).getTitle().split(",");
            String[] amounts = bar.get(i).getAmount().split(",");
            for (int k = 0; k < titles.length; k++) {
                if (!seriesMap.containsKey(titles[k])) {
                    series = new SeriesLine();
                    series.setName(titles[k]);
                    series.setType(Type.line);
                    series.setStack("总量");
                    series.setLabel(new Label(new Normal()));
                    series.getLabel().getNormal().setShow(Boolean.TRUE.getValue());
                    series.getLabel().getNormal().setPosition(Position.top);
                    series.getLabel().getNormal().setFormatter("{c}");
                    series.setData(new String[bar.size()]);
                    series.setAreaStyle(new AreaStyle());
                    seriesMap.put(titles[k], series);
                }
                seriesMap.get(titles[k]).getData()[i] = amounts[k];
            }
        }

        seriesMap.forEach((key, value) -> {
            for (int i = 0; i < value.getData().length; i++) {
                if (StringUtil.isBlank(value.getData()[i])) {
                    value.getData()[i] = "0.00";
                }
            }
            option.getSeries().add(value);
        });

        Title title = new Title();
        title.setText("费用年度明细");
        title.setSubtext(year + "年度");
        title.setX(HorizAlign.center);
        option.setTitle(title);

        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger(Trigger.axis);
        option.setTooltip(tooltip);

        Legend legend = new Legend();
        legend.setOrient(Orient.vertical);
        legend.setX(HorizAlign.right);
        legend.setData(new String[]{"燃气费", "水费", "电费"});
        option.setLegend(legend);

        XAxis xAxis = new XAxis();
        xAxis.setType(XAxis.Type.category);
        xAxis.setBoundaryGap(Boolean.FALSE.getValue());
        xAxis.setData(month);
        option.setxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setType(XAxis.Type.value);
        option.setyAxis(yAxis);

        return option;
    }

    public static OptionLine buildLineScatterGram(List<PieVO> pie, String year, Integer type) {
        String[] month = new String[pie.size()];
        String[] datas = new String[pie.size()];

        List<Pieces> pieces = ListUtil.getInstance();

        List<List<DataMA>> dms = ListUtil.getInstance();

        int index = 0;

        for (int i = 0; i < pie.size(); i++) {
            month[i] = pie.get(i).getTitle() + "月";
            datas[i] = pie.get(i).getAmount().toString();
            // 不是第一和最后一个
            if (i != 0 && i < pie.size() - 1) {
                // 如果比前面一个大，并且比下一个小
                if (pie.get(i).getAmount().compareTo(pie.get(i - 1).getAmount()) == 1 &&
                        pie.get(i).getAmount().compareTo(pie.get(i + 1).getAmount()) == 1) {
                    List<DataMA> dm = ListUtil.getInstance();
                    dm.add(new DataMA("用" + (type.equals(0) ? "水" : (type.equals(1) ? "电" : "气")) + "高峰", pie.get(i - 1).getTitle() + "月"));
                    dm.add(new DataMA(null, pie.get(i).getTitle() + "月"));
                    dms.add(dm);
                    if (index == 0 && i - 1 != 0) {
                        pieces.add(new Pieces(i - 1, Pieces.Color.green));
                    } else if (index != 0 && index != i - 1) {
                        pieces.add(new Pieces(index, i - 1, Pieces.Color.green));
                    }
                    if (i - 1 == 0) {
                        pieces.add(new Pieces(i, Pieces.Color.red));
                    } else {
                        pieces.add(new Pieces(i - 1, i, Pieces.Color.red));
                    }
                    index = i;
                }
            }
        }
        if (index != pie.size()) {
            pieces.add(new Pieces(index));
        }

        OptionLine option = new OptionLine();

        SeriesLine series = new SeriesLine();
        series.setName("用" + (type.equals(0) ? "水" : (type.equals(1) ? "电" : "气")) + "量");
        series.setType(Type.line);
        series.setSmooth(Boolean.TRUE.getValue());
        series.setData(datas);
        if (dms.size() > 0) {
            MarkArea markArea = new MarkArea(dms);
            series.setMarkArea(markArea);
        }
        option.getSeries().add(series);

        Title title = new Title();
        title.setText(year + "年度" + series.getName() + "分布");
        option.setTitle(title);

        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger(Trigger.axis);
        option.setTooltip(tooltip);

        XAxis xAxis = new XAxis();
        xAxis.setType(XAxis.Type.category);
        xAxis.setBoundaryGap(Boolean.FALSE.getValue());
        xAxis.setData(month);
        option.setxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setType(XAxis.Type.value);
        yAxis.setAxisLabel(new AxisLabel("{value}" + (type.equals(0) ? "立方米" : (type.equals(1) ? "千瓦时" : "立方米"))));
        option.setyAxis(yAxis);

        VisualMap vm = new VisualMap(Boolean.FALSE.getValue(), 0, pieces);
        option.setVisualMap(vm);

        return option;
    }
}
