


function open_popup(form) {
    window.open('', 'formpopup', 'width=800,height=350,resizeable,scrollbars,status=0,titlebar=0');
    form.target = 'formpopup';
}

$(document).ready(function() {
      $('.modal-trigger').leanModal({
       dismissible: true,
       opacity: .5,
       in_duration: 200,
       out_duration: 200,
       starting_top: '10%'
    });
  $('.parallax').parallax();

  Materialize.toast(" Enter Input for the Model ",5000);
});