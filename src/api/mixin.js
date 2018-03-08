import {toThousands,toLocalString} from './method'
export default {
    data(){
        return {
            heightObj:''
        }
    },
    methods:{
        showToast(status,succMes,failMes){
            if(status.toLocaleLowerCase()=='success'){
                this.$message({
                    type: 'success',
                    message: succMes
                });
                return true;
            } else {
                this.$message({
                    type: 'warning',
                    message: failMes
                });
                return false;
            }
        },
        scrolltoview(eletoview,message){
            var obj=this.$refs[eletoview];
            if(!this.$refs[message]){
              return;
            }
            if(!this.heightObj){
                this.heightObj=this.$refs[message].offsetHeight
            }
            if(!obj){
                this.$refs[message].style.height=43+"px";
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
        },
        numberFormat: function (row, column) {
            var num = row[column.property];
            if (num == undefined) {
            return "";
            }
            return toThousands(num);
        },
        formatDate(r,c,time){
            if (time == null || time == ''){
                return '';
            }
            return toLocalString(time);
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