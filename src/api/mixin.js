export default {
    data(){
        return {
            heightObj:{}
        }
    },
    methods:{
        scrolltoview(eletoview,arr){
            var obj=this.$refs[eletoview];
            console.log(eletoview);
            console.log(arr);
            if(this.heightObj=={}){
                for(var j in arr){
                    this.heightObj[j]=this.$refs[j].offsetHeight
                }
            }
            if(obj){
                var scrolly=window.scrollY;
                var _top=obj.getBoundingClientRect().top;
                var top=_top+scrolly-130;
                document.documentElement.scrollTop=top;
                if(arr[eletoview]){
                    obj.style.height=this.heightObj[eletoview];
                }else{
                    
                }
                
            }
            
                // for(var i in arr){
                //     //if(this.$refs[i]){
                //         if(!arr[i]){
                            
                //             this.$refs[i].style.height=50+'px';
                //             this.$refs[i].style.overflow='hidden';
                            
                //         }else{
                            
                //                 this.$refs[i].style.height=this.heightObj[i]+'px';
                            
                //         }
                //     //}
                // }
                if(arr[eletoview]){

                }
                
            
        }
    }
}