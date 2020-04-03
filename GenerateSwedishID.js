const candidateNumber = () => {
    var ry  = Math.floor(Math.random() * (1960 - 2000) + 2000);
    var rm = Math.floor(Math.random() * 11);
    var rd = Math.floor(Math.random() * 31) + 1;
    var d = new Date(ry, rm, rd),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();
    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;
    
    var part1 = [year, month, day].join('');
    
    const last3 = Math.floor(Math.random() * (9999 - 1000) + 1000);
    
    const numberMinus = part1 + last3;
    
    return  numberMinus
}

console.log(candidateNumber());
