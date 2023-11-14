function showName(name) {
    const message = document.getElementById('showN');
    // alert(name);
    message.classList.add("timednotific");
    message.style.display = 'block';
    message.innerHTML = 'Welcome back again ' + name;
    
    setTimeout(() => {
        message.classList.remove("timednotific");
        message.style.display = 'none';
    }, 3000);

}