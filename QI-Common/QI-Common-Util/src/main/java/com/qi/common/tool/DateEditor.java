package com.qi.common.tool;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * Class DateEditor
 *
 * @author 张麒 2016/8/3.
 * @version Description:
 */
public class DateEditor extends PropertyEditorSupport {

    private String defaultPattern = "yyyy-MM-dd HH:mm:ss.SSS";

    public DateEditor() {
    }

    public DateEditor(String defaultPattern) {
        this.defaultPattern = defaultPattern;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isNotBlank(text)) {
            try {
                this.setValue(DateUtils.parseDate(text, this.defaultPattern, "yyyy-MM-dd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"));
            } catch (ParseException var3) {
                throw new IllegalArgumentException(var3.getMessage(), var3);
            }
        } else {
            this.setValue((Object) null);
        }

    }

    public String getAsText() {
        return this.getValue() != null ? DateFormatUtils.format((Date) this.getValue(), this.defaultPattern) : null;
    }
}
