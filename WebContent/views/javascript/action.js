var slideIndex = 1;
var buttonDown = '';
var timer = null;
var pagingPre = document.getElementById('pagingPre');
var pagingNext = document.getElementById('pagingNext');
var imageInfor = document.getElementsByClassName('imageInfor');

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
            document.getElementById('imageName').focus();
            document.getElementById('alternateText').value = alternateText;
            // Tạo param [userId] cho form
            var imageId = document.createElement("input");
            imageId.type = "hidden";
            imageId.name = "imageId";
            imageId.id = "imageId";
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


document.addEventListener('keydown', (event) => {
    var name = event.key;
    console.log(name);
    switch (name) {
        case 'Shift':
            buttonDown = 'Shift';
            break;
        case 'ArrowLeft':
            if (buttonDown === 'Shift') {
                if (pagingPre) {
                    pagingPre.click();;
                }
            } else {
                plusSlides(-1);
            }
            break;
        case 'ArrowRight':
            if (buttonDown === 'Shift') {
                if (pagingNext) {
                    pagingNext.click();
                }
            } else {
                plusSlides(+1);
            }
            break;
        case '1':
            if (imageInfor[0]) {
                imageInfor[0].click();
            }
            break;
        case '2':
            if (imageInfor[1]) {
                imageInfor[1].click();
            }
            break;
        case '3':
            if (imageInfor[2]) {
                imageInfor[2].click();
            }
            break;
        case '4':
            if (imageInfor[3]) {
                imageInfor[3].click();
            }
            break;
        case '5':
            if (imageInfor[4]) {
                imageInfor[4].click();
            }
            break;
        case '6':
            if (imageInfor[5]) {
                imageInfor[5].click();
            }
            break;
        case '7':
            if (imageInfor[6]) {
                imageInfor[6].click();
            }
            break;
        case '8':
            if (imageInfor[7]) {
                imageInfor[7].click();
            }
            break;
        case '9':
            if (imageInfor[8]) {
                imageInfor[8].click();
            }
            break;
        case 'Enter':
            if (document.getElementsByClassName('confirm')[0].style.display === "block") {
                Confirm.ok();
            }
            break;
        case 'Escape':
            document.getElementsByClassName('confirm')[0].style.display = "none";
            break;
        default:
            buttonDown = '';
    }

    if (timer) {
        clearTimeout(timer);
    }
    timer = setTimeout(() => {
        console.log('buttonDown:', buttonDown)
        buttonDown = '';
    }, 1000);


}, false);

// Tạo đối tượng confirmDialog => Hiển thị ra hộp thoại confirm
var Confirm = new CustomConfirm();
