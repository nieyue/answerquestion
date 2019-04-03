/**
 * 长文章分页
 * 冗余：单页最多2000-50个字，最多30个段
 * 也就是说，在考虑图片的范围内，单页最多有16个段落中有图片，最多16”行“图片
 */
;
(function(a){
    var defaultOptions = {
        'maxCharSize' : 1000,//单页最大字数
        'safeCutCharSize' : 50,//冗余字数，用于相减
        'maxPr' : 30,//单页最大段落数30
        'pageContent' : '#page-box',//分页容器
        'pTag' : 'p',//分段标志
        'pagedPix' : 'article_',//分页后容器id前缀
        'contentPageClass' : 'contentPageClass',//每个分类容器的类型
    };
    a.fn.pagenation = function(options){
        //数组合并
        var options = $.extend(defaultOptions, options);
        //首先排除不需要分页的情况，单页小于2000字，字段小于单页最大段落数
        var content = this;
        var contentLen = content.text().length;
        //所有段落保存进数组
        //var pList = content.find(options.pTag);
        var pList = content.children();
        var pListLen = pList.length;
        if(!isOverCharSize(contentLen, options)&&!isOverPrSize(pListLen, options)){
            //不需要分段，删除分页容器
            //$(options.pageContent).css('display', 'none');
        }else if(!isOverCharSize(contentLen, options)){
            //这里就不考虑段落数了，只要少于2000字，就不分页，不管多少段
           //$(options.pageContent).css('display', 'none');
        }
        //考虑分段的情况
        //都超过了
        if(isOverCharSize(contentLen, options)&&isOverPrSize(pListLen, options)){
            pagenation(content, contentLen, pList, pListLen, options);
        }
        //字不超，段超了
        if(!isOverCharSize(contentLen, options)&&isOverPrSize(pListLen, options)){
            pagenation(content, contentLen, pList, pListLen, options);
        }
        //字超了，段不超
        if(isOverCharSize(contentLen, options)&&!isOverPrSize(pListLen, options)){
            pagenation(content, contentLen, pList, pListLen, options);
        }
    };
    /**
     * 输入长度直接检测
     * @param len
     * @param options
     * @returns {boolean}
     */
    var isOverCharSize = function(len, options){
        return (len > (options.maxCharSize-options.safeCutCharSize)) ? true : false;
    }
    /**
     * 是否超过段落
     * @param obj
     * @param options
     * @returns {boolean}
     */
    var isOverPrSize = function(len, options){
      return (len >= options.maxPr) ? true : false;
    };

    /**
     * 分页方法
     * @param pList
     * @param options
     */
    var pagenation = function(content, contentLen, pList, pListLen, options){
        content.css('display', 'none');
        content.empty();
        //当前页码
        var currentPageIndex = 1;
        //当前域
        var currentPagedContent = setPagedContent(content, options.pageContent, options, currentPageIndex);
        //当前文本
        var currentText = '';
        //采用拼接判断的办法
        for(var i=0;i<pList.length;i++){
            var currentP = pList[i];
            //判断当前有没有超过
            //没有超过
            //因为i从0开始记，所以这里要+1
            var index = i+1;
            if(!isOverCharSize((currentText+currentP.innerText).length, options)&&(index-options.maxPr<=0)){
                currentText = currentText+currentP.innerText;
            }else{
                currentText = '';
                currentPageIndex++;
                currentPagedContent = setPagedContent(content, options.pageContent, options, currentPageIndex);
            }
            $(currentPagedContent).append($(currentP).clone());
        }
        //显示所有元素
        //content.find(options.pTag).css('display', 'block');
        //content.children().css('display', 'block');
        content.css('display', 'block');
    };

    /**
     * 创建分页后的容器，返回id，提供查找方向
     * @param content
     * @param index
     */
    var setPagedContent = function(content, pageContent, options, index){
        var id = options.pagedPix+index;
        if(index == 1){
            var text = '<div class=\''+options.contentPageClass+'\' id="'+id+'"></div>';
        }else{
            var text = '<div class=\''+options.contentPageClass+'\' id="'+id+'" style="display:none;"></div>';
        }
        //添加分页内容
        content.append(text);
        //添加页码
        $(pageContent).append('<a class="pageIndex" href="javascript:showPage(\'#'+id+'\', \'.'+options.contentPageClass+'\')">'+index+'</a>');
        return '#'+id;
    };
})(jQuery);

/**
 * 分页显示
 * @param id
 * @param options
 */
var showPage = function(id, findClass){
    $(findClass).each(function(){
        $(this).css('display', 'none');
    });
    //显示当前项
    $(id).css('display', 'block');
};