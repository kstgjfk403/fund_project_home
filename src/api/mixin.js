import {toThousands} from './method'
export default {
    data(){
        return {
            heightObj:''
        }
    },
    methods:{
        showToast(status,type,succMes,failMes){
            if(status.toLocaleLowerCase()=='success'){
                this.$message({
                    type: type,
                    message: succMes
                });
                return true;
            } else {
                this.$message({
                    type: type,
                    message: failMes
                });
                return false;
            }
        },
        scrolltoview(eletoview,message){
            var obj=this.$refs[eletoview];
            if(!this.heightObj){
                this.heightObj=this.$refs[message].offsetHeight
            }
            if(!obj){
                this.$refs[message].style.height=50+"px";
                this.$refs[message].style.overflow='hidden';
            }
            if(obj){
                var scrolly=window.scrollY;
                var _top;
                setTimeout(function(){
                    _top=obj.getBoundingClientRect().top;
                    document.documentElement.scrollTop=_top;
                },0)
                obj.style.height=this.heightObj+"px"
            }
        }
    },
    filters:{
        transformNum(num){
            if(typeof num != 'number'){
                return ''
            }
            return toThousands(num);
        }
    },
}