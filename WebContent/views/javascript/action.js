var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    if (n > slides.length) { slideIndex = 1 }
    if (n < 1) { slideIndex = slides.length }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slides[slideIndex - 1].style.display = "block";
}

function sendReloadRequest() {
    document.location.href = 'reloadImage.do';
}

/**
 * Định nghĩa đối tượng confirm
 */
class CustomConfirm {
    constructor() {
        // Tạo hộp thoại dialog
        this.dialog = function (imageName, alternateText, imageIdVal) {
            // Hiển thị hộp thoại
            document.getElementsByClassName('confirm')[0].style.display = "block";
            // Hiển thị câu thông báo lên hộp thoại
            document.getElementById('confirmMessage').innerHTML = 'Update Image Infor';
            document.getElementById('imageName').value = imageName;
            document.getElementById('alternateText').value = alternateText;
            // Tạo param [userId] cho form
	        var imageId = document.createElement("input");
	        imageId.type = "hidden";
	        imageId.name = "imageId";
	        imageId.id   = "imageId";
	        imageId.value = String(imageIdVal);
            // Tạo form mới
            var form = document.getElementById('formUpdate');
            // set các giá trị cho form
            form.appendChild(imageId);
        };
        // Nếu click vào button [Cancel]
        this.cancel = function () {
            document.getElementsByClassName('confirm')[0].style.display = "none";
            var form = document.getElementById('formUpdate');
            var imageId = document.getElementById('imageId');
            form.removeChild(imageId);
        };
        // Nếu click vào [OK]
        this.ok = function () {
            document.getElementsByClassName('confirm')[0].style.display = "none";
            var form = document.getElementById('formUpdate');
            // submit form
            form.submit();
        };
    }
}

// Tạo đối tượng confirmDialog => Hiển thị ra hộp thoại confirm
var Confirm = new CustomConfirm();
