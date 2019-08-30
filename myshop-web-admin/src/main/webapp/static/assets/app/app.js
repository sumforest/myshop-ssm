var APP = function () {
    //主选择框
    var ichecked_master ;
    //副选择框
    var ichecked_all;
    //存放批量删除id的数组
    var ids;

    var defaultOptions = {
        url: "/upload", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "dropFile", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传"+ this.maxFiles +"个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消"
    };
    /**
     * 初始化checkbox插件
     */
    var initCheckBox = function () {
        $('input[type="checkbox"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
        });
        //找出主选择框
        ichecked_master = $('input[type="checkbox"].minimal.ichecked_master');
        //找出副选择框
        ichecked_all = $('input[type="checkbox"].minimal.icheck_all');
    };

    /**
     * 全选的方法
     */
    var initAllChecked = function () {
        ichecked_master.on('ifClicked', function (e) {
            if (e.target.checked) {
                ichecked_all.iCheck('uncheck');
            } else {
                ichecked_all.iCheck('check');
            }
        });
    };
    /**
     * 删除单个
     * @param url
     * @param id
     */
    var handlerDelSingle = function (url,id,message) {
        if (!message) message = null;
        // 接收id的数组
        ids = new Array();
        ids.push(id);
        if (message == null) {
            $("#modal-message").html("此操作将会删除所选中的目录和目录的所有内容，您确定要删除吗？");
        } else {
            $("#modal-message").html(message);
        }
        $("#modal-default").modal('show');
        $("#ModalBtnOk").bind("click", function () {
            HandlerAjaxDel(ids,url);
        })
    };
    /**
     * 批量删除相关操作
     */
    var deleteMulti = function (url) {
     // 接收id的数组
        ids = new Array();
        ichecked_all.each(function () {
                var id = $(this).attr("id");
                //判断当前的用户是否被选中
                if (id != null && id != "undefined" && $(this).is(":checked")) {
                    //把符合被选中的id放进数组内
                    ids.push(id);
                }
            });
            //模态框的相关提示
            if (ids.length === 0) {
                $("#modal-message").html("您还没有选择任何数据，请至少选择一条数据");
            } else {
                $("#modal-message").html("您确定要删除您所选择的数据吗？");
            }
            $("#modal-default").modal('show');
            //为按钮添加一个点击事件
            $("#ModalBtnOk").bind("click", function () {
               HandlerAjaxDel(ids,url);
            })


    };
    /**
     * 异步删除
     * @param url
     */
    var HandlerAjaxDel = function (ids,url) {
            //点击确定按钮之后让模态框消失
            $("#modal-default").modal('hide');
            if (ids.length > 0) {
                setTimeout(function () {
                    $.ajax({
                        "url": url,
                        "type": "POST",
                        "data": {"ids": ids.toString()},
                        //返回参数类型
                        "dataType": "JSON",
                        "success": function (data) {
                            //给确定按钮解绑点击事件
                            $("#ModalBtnOk").unbind('click');
                            if (data.status == 200) {
                                $("#modal-message").html(data.message);
                                $("#modal-default").modal('show');
                                //给确定按钮重新绑定点击事件
                                $("#ModalBtnOk").bind('click',function () {
                                    $("#modal-default").modal('hide');
                                    window.location.reload();
                                });

                            } else {
                                //给确定按钮重新绑定点击事件
                                $("#ModalBtnOk").bind('click',function () {
                                    $("#modal-default").modal('hide');
                                });
                                $("#modal-message").html(data.message);
                                $("#modal-default").modal('show');
                            }
                        }
                    })
                },300)
            }
    };
    /**
     * DataTables分页查询
     * @param url
     * @param columns
     * @returns {jQuery}
     */
    var handlerPage = function (url,columns) {
        var searchTable = $("#datable-mytables").DataTable({
            "paging":true,
            "info":true,
            "lengthChange": false,
            "ordering":  false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url
            },
            "columns":columns,
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function(settings) {
                initCheckBox();
                initAllChecked();
            }
        });
        return searchTable;
    };
    /**
     * 显示详情
     * @param url
     */
    var handlerShowDetail = function (url) {
            $.ajax({
                url:url,
                type:"GET",
                dataType:"HTML",
                success:function (data) {
                    $("#modal-detail-message").html(data);
                    $("#modal-detail").modal('show');
                }
            })
    };
    /**
     * 初始化ztree
     * @param url
     * @param autoParam 自动发送给服务器的参数
     * @param callback 回调函数
     */
    var handlerzTree = function (url,autoParam,callback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam
            }
        };

        $(document).ready(function () {
            $.fn.zTree.init($("#myTree"), setting);
            $("#ModalBtnOk").bind("click", refreshNode);
        });

        function refreshNode(e) {
            var zTree = $.fn.zTree.getZTreeObj("myTree"),
                nodes = zTree.getSelectedNodes();//获取选中的节点
            //判断是否选择条目
            if (nodes.length == 0) {
                alert("请先选择一个父节点");
            } else {
                callback(nodes);//回调函数执行相应逻辑
            }
        }
    };
    /**
     * 初始化dropzone
     * dropzone和jQuery会冲突
     * @param dropZoneId
     * @param opts 参数对象
     */
    var handlerDropZone = function (dropZoneId,opts) {
        Dropzone.autoDiscover = false;
        //利用jQuery的继承，opts重写defaultOptions里面的属性
        $.extend(defaultOptions, opts);
        new Dropzone(dropZoneId, defaultOptions);
    }
    /**
     * 初始化
     */
    return {
        /**
         * checkbox的初始化已经交给DataTables管理
         */
        init: function () {
            initCheckBox();
            initAllChecked();
        },
        /**
         * 初始化批量删除
         * @param url
         */
        deleteHandlerMuilti: function (url) {
            deleteMulti(url);
        },
        /**
         * 初始化分页组件dataTables
         * @param url
         * @param columns
         * @returns {jQuery}
         */
        initHandlerPage: function (url, columns) {
            return handlerPage(url, columns);
        },
        /**
         * 初始化查看详情
         * @param url
         */
        initHandlerShowDetail: function (url) {
            handlerShowDetail(url);
        },
        /**
         * 初始化zTree
         * @param url
         * @param autoParam
         * @param callback
         */
        intiHandlerzTree: function (url, autoParam, callback) {
            handlerzTree(url, autoParam, callback);
        },
        /**
         * 初始化DropZone
         * @param dropZoneId
         * @param opts
         */
        intHandlerDropZone: function (dropZoneId, opts) {
            handlerDropZone(dropZoneId, opts);
        },
        /**
         * 初始化删除单条记录
         * @param url
         * @param id
         * @param message
         */
        initHandlerDelSingle:function (url,id,message) {
            handlerDelSingle(url,id,message)
        }


    }

}();
