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

function randomUser(){
    var userName = document.getElementById("user-name");
    var userGender = document.getElementById("user-gender");
    var userImage = document.getElementById("user-image");

    fetch("https://randomuser.me/api")
        .then(function(res){
            return res.json();
        })
        .then(function(data){
            userName.innerHTML = data.results[0].name.first + " " + data.results[0].name.second;
            userGender.innerHTML = data.results[0].gender;
            userImage.src = data.results[0].picture.large;
        })
        .then(function(err){
            console.log("Error Occured : " + err);
        })
}