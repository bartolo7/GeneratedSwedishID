const generteSwedishIdNumber = () => {
  var ry = Math.floor(Math.random() * (1960 - 2000) + 2000);
  var rm = Math.floor(Math.random() * 11);
  var rd = Math.floor(Math.random() * 31) + 1;

  var d = new Date(ry, rm, rd),
    month = "" + (d.getMonth() + 1),
    day = "" + d.getDate(),
    year = d.getFullYear();
  if (month.length < 2) month = "0" + month;
  if (day.length < 2) day = "0" + day;

  var part1 = [year, month, day].join("");

  const last3 = Math.floor(Math.random() * (9999 - 1000) + 1000);

  const numberMinus = part1 + last3;
  if (validatePersonalNumber(numberMinus)) {
    return numberMinus;
  } else {
    return generteSwedishIdNumber();
  }
};

const validatePersonalNumber = (input) => {
  // Check valid length & form
  if (!input) return false;

  if (input.indexOf("-") == -1) {
    if (input.length === 10) {
      input = input.slice(0, 6) + "-" + input.slice(6);
    } else {
      input = input.slice(0, 8) + "-" + input.slice(8);
    }
  }
  if (
    !input.match(
      /^(\d{2})(\d{2})(\d{2})\-(\d{4})|(\d{4})(\d{2})(\d{2})\-(\d{4})$/
    )
  )
    return false;

  // Clean input
  input = input.replace("-", "");
  if (input.length == 12) {
    input = input.substring(2);
  }

  // Declare variables
  var d = new Date(
      !!RegExp.$1 ? RegExp.$1 : RegExp.$5,
      (!!RegExp.$2 ? RegExp.$2 : RegExp.$6) - 1,
      !!RegExp.$3 ? RegExp.$3 : RegExp.$7
    ),
    sum = 0,
    numdigits = input.length,
    parity = numdigits % 2,
    i,
    digit;

  // Check valid date
  if (
    Object.prototype.toString.call(d) !== "[object Date]" ||
    isNaN(d.getTime())
  )
    return false;

  // Check luhn algorithm
  for (i = 0; i < numdigits; i = i + 1) {
    digit = parseInt(input.charAt(i));
    if (i % 2 == parity) digit *= 2;
    if (digit > 9) digit -= 9;
    sum += digit;
  }
  return sum % 10 == 0;
};

// Just run in the terminal node 
console.log(generteSwedishIdNumber());
