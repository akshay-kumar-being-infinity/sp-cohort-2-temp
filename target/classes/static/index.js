// alert("Hello world");
console.log("hello from java script");

var users = [
    {
        "name" : "John Doe",
        "image" : "/images/john.png",
        "gender" : "Male"
    },
    {
        "name" : "Jane Doe",
        "image" : "/images/jane.png",
        "gender" : "Female"
    }
]

var id = 0;

function toggleUser(){
    var userName = document.getElementById("user-name");
    var userGender = document.getElementById("user-gender");
    var userImage = document.getElementById("user-image");


    id = ( id + 1 ) % users.length;

    userName.innerHTML = users[id].name;
    userGender.innerHTML = users[id].gender;
    userImage.src = users[id].image;
}