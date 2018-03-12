// 导出页面为PDF格式  
import html2Canvas from 'html2canvas'  
import JsPDF from 'jspdf'  
export default{  
  install (Vue, options) {  
    Vue.prototype.getPdf = function () {  
      var title = this.htmlTitle
      var shareContent = document.querySelector('#pdfDom'); 
      var width = shareContent.offsetWidth; 
      var height = shareContent.offsetHeight; 
      var canvas = document.createElement("canvas"); 
      var scale = 4; 

      canvas.width = width * scale; 
      canvas.height = height * scale; 
      canvas.getContext("2d").scale(scale, scale); 

      var opts = {
          scale: scale, 
          canvas: canvas, 
          logging: true, 
          width: width, 
          height: height,
          /* allowTaint: true, */
          useCORS:true 
      };  
      html2Canvas(document.querySelector('#pdfDom'), opts).then(function (canvas) {
        var context = canvas.getContext('2d');
        context.scale(1/scale, 1/scale);
        // 【重要】关闭抗锯齿
        context.mozImageSmoothingEnabled = true;
        context.webkitImageSmoothingEnabled = true;
        context.msImageSmoothingEnabled = true;
        context.imageSmoothingEnabled = true;  
        let contentWidth = canvas.width  
        let contentHeight = canvas.height 
        let pageHeight = contentWidth / 592.28 * 841.89  
        let leftHeight = contentHeight  
        let position = 0;  
        let imgWidth = 595.28;  
        let imgHeight = 592.28 / contentWidth * contentHeight;
        let pageData = canvas.toDataURL('image/jpeg', 1.0)  
        let PDF = new JsPDF('', 'pt', 'a4')  
        if (leftHeight < pageHeight) {  
          PDF.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight)  
        } else {  
          while (leftHeight > 0) {  
            PDF.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)  
            leftHeight -= pageHeight  
            position -= 841.89  
            if (leftHeight > 0) {  
              PDF.addPage()  
            }  
          }  
        }  
        PDF.save(title + '.pdf');         
      })
    }  
  }  
}  