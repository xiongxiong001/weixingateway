package org.demoexm.core.vo;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONObject;

public class PageResultVo<T> implements Serializable
{
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 5807693841154514115L;
    
    /**
     * 分页参数信息
     */
    private PageVo page;
    
    /**
     * 数据结果列表
     */
    private List<T> resultList;
    
    public PageResultVo()
    {
        super();
    }
    
    public PageResultVo(PageVo page, List<T> resultList)
    {
        this.page = page;
        this.resultList = resultList;
    }
    
    public PageVo getPage()
    {
        return page;
    }
    
    public void setPage(PageVo page)
    {
        this.page = page;
    }
    
    public List<T> getResultList()
    {
        return resultList;
    }
    
    public void setResultList(List<T> resultList)
    {
        this.resultList = resultList;
    }
    
    /**
     * 分页参数 - json
     * 
     * @return
     */
    public JSONObject getParameters()
    {
        JSONObject object = new JSONObject();
        object.put("totalPages", page.getTotalPages());
        object.put("number", page.getPageCount());
        object.put("pageSize", page.getPageSize());
        object.put("count", page.getCount());
        object.put("showPageNumer", page.getShowPageNumer());
        return object;
    }
}