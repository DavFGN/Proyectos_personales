function comprobar() {

    let passA = document.getElementById('passA').value;
    let passN = document.getElementById('passN').value;
    let passNR = document.getElementById('passNR').value;
    let bot = document.getElementById('bot');

    if (passA != "" & passN != "" & passNR != "") {

        if (passN == passNR) {

            bot.disabled = false;

        } else {
            bot.disabled = true;
        }


    } else {
        bot.disabled = true;
    }

}
