export const judgeDate = (date)=> {
    const kijitsu = new Date(date);
    const today = new Date();
    
    //console.log(kijitsu.getFullYear() + "-" + kijitsu.getMonth() + "-" + kijitsu.getDate());
    //console.log(today.getFullYear() + "-" + today.getMonth() + "-" + today.getDate());
    //console.log(isExpired);

    return kijitsu <= today;
}