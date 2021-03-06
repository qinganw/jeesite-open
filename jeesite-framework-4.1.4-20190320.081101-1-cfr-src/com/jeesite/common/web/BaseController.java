/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.web;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.web.http.ServletUtils;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.jni.ArchNotSupportedException;	
import org.hyperic.sigar.SudoFileInputStream;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.annotation.Value;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.WebDataBinder;	
import org.springframework.web.servlet.mvc.support.RedirectAttributes;	
	
public abstract class BaseController {	
    protected static final String FORWARD = "forward:";	
    protected static final String REDIRECT = "redirect:";	
    @Value(value="${frontPath}")	
    protected String frontPath;	
    @Value(value="${adminPath}")	
    protected String adminPath;	
    public static final String WEB_DATA_BINDER_TARGET;	
    public static final String WEB_DATA_BINDER_SOURCE;	
    protected Logger logger;	
	
    protected String renderResult(HttpServletResponse response, String result, String message, Object data) {	
        return ServletUtils.renderResult(response, result, message, data);	
    }	
	
    protected String renderResult(String result, String message, Object data) {	
        return ServletUtils.renderResult(result, message, data);	
    }	
	
    public static /* varargs */ String text(String code, String ... params) {	
        return Global.getText(code, params);	
    }	
	
    protected String renderResult(String result, StringBuilder message) {	
        return this.renderResult(result, message != null ? message.toString() : "");	
    }	
	
    protected /* varargs */ void addMessage(RedirectAttributes redirectAttributes, String ... messages) {	
        int n;	
        StringBuilder a = new StringBuilder();	
        String[] arrstring = messages;	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            String a2 = arrstring[n];	
            a.append(a2).append(messages.length > 1 ? "<br/>" : "");	
            n3 = ++n;	
        }	
        redirectAttributes.addFlashAttribute("message", a.toString());	
    }	
	
    protected String renderResult(HttpServletResponse response, String result, String message) {	
        return ServletUtils.renderResult(response, result, message, null);	
    }	
	
    protected <E> E getWebDataBinderTarget(HttpServletRequest request) {	
        return (E)request.getAttribute(WEB_DATA_BINDER_TARGET);	
    }	
	
    static {	
        WEB_DATA_BINDER_SOURCE = new StringBuilder().insert(0, WebDataBinder.class.getName()).append(".SOURCE").toString();	
        WEB_DATA_BINDER_TARGET = new StringBuilder().insert(0, WebDataBinder.class.getName()).append(".TARGET").toString();	
    }	
	
    public BaseController() {	
        BaseController baseController = this;	
        baseController.logger = LoggerFactory.getLogger(baseController.getClass());	
    }	
	
    protected /* varargs */ void addMessage(Model model, String ... messages) {	
        int n;	
        StringBuilder a = new StringBuilder();	
        String[] arrstring = messages;	
        int n2 = arrstring.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            String a2 = arrstring[n];	
            a.append(a2).append(messages.length > 1 ? "<br/>" : "");	
            n3 = ++n;	
        }	
        model.addAttribute("message", a.toString());	
    }	
	
    protected String renderResult(String result, String message) {	
        return this.renderResult(result, message, null);	
    }	
	
    protected <E> E getWebDataBinderSource(HttpServletRequest request) {	
        return (E)request.getAttribute(WEB_DATA_BINDER_SOURCE);	
    }	
}	
	
