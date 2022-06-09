import CryptoJS from 'crypto-js'

export const encrytParam=(param)=>{
    var key = CryptoJS.enc.Utf8.parse("ffea52a9ae99bbcb");
    var parsedParam = CryptoJS.enc.Utf8.parse(param);
    var encrypted = CryptoJS.AES.encrypt(parsedParam, key, { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 });
    encrypted = (encrypted.toString().includes("/") ? encrypted.toString().replace(new RegExp('/', 'g'), "92AS") : encrypted.toString());
    return encrypted.toString();
}

export const decrypt = (encParam) => {
    encParam = (encParam.toString().includes("92AS") ? encParam.toString().replace(new RegExp('92AS', 'g'), "/") : encParam.toString());
    var key = CryptoJS.enc.Utf8.parse("ffea52a9ae99bbcb");
    var decrypt = CryptoJS.AES.decrypt(encParam, key, { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 });
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();
}