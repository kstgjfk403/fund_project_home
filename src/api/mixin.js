export default {
    methods:{
        scrolltoview(eletoview){
            var obj=this.$refs[eletoview];
            if(obj){
                var scrolly=window.scrollY
                var _top=obj.getBoundingClientRect().top;
                var top=_top+scrolly-130;
                document.documentElement.scrollTop=top;
            }
            
        }
    }
}