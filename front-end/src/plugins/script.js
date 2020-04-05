export const getCookie = (name) => {
    let value = "; " + document.cookie;
    let parts = value.split("; " + name + "=");
    if(parts.length == 2) return parts.pop().split(";").shift();
}

export const deleteCookie = (name) => {
    document.cookie = name + "=; Path=/;"
}