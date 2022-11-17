let loginForm = document.getElementById('login-form')
let loginUsername = document.getElementById('login-username')
let loginPassword = document.getElementById('login-password')
let showPassword = document.querySelector("#showPassword");

const headers = {
    'Content-Type':'application/json'
}

//const baseUrl = 'http://localhost:8085/api/v1/users'
const baseUrl = 'api/v1/users'


const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        username: loginUsername.value,
        password: loginPassword.value
    }

    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
     })
        .catch(err => console.error(err.message))

    const responseArr = await response.json()
    console.log(responseArr);

    if (response.status === 200){
        document.cookie = `userId=${responseArr[1]}=${responseArr[2]}`
        window.location.replace(responseArr[0])
    }
}

function showPasswordField(){

    if(showPassword.checked === true){
        loginPassword.type = "text";
    }
    else
    {
        loginPassword.type = "password";
    }
}

loginForm.addEventListener("submit", handleSubmit);
showPassword.addEventListener("click", showPasswordField)

