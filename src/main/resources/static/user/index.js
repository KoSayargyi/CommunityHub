function goToCreatePost(){
    window.location.href = '/user/goToCreatePost'
}

const fileInput = document.getElementById('file');

fileInput.addEventListener('change', () => {
    const file = fileInput.files[0];
const fileName = file.name.toLowerCase();
const validExtensions = ['jpg', 'jpeg', 'png','gif','bmp','tiff','tif','psv','svg','webp','ico','heic','mp4','avi','mkv','mov','wmv','flv','mpeg','mpg','webm','3gp','ts'];

if (!validExtensions.includes(fileName.split('.').pop())) {
    alert('Invalid file type. Please select a JPG, JPEG or PNG file.');
    fileInput.value = '';
}
});




async function createPost(){
    let data = new FormData(document.getElementById('postForm'))
    console.log(data)
    let response = await fetch('/post/createPublicPost',{
        method : 'POST',
        body : data
    })
    console.log(response)
}

