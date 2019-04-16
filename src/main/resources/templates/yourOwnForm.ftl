<!DOCTYPE html>
<html lang="en">
  <script type="text/javascript" src="https://api.paymentwall.com/brick/brick.1.4.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<body>
  <form id="brick-creditcard-form" action="/form-action" method="POST">
    <input name="custom_parameter" type="hidden" value="custom_value"/>
    <div>
      <label>
        <span>Card number</span>
        <input data-brick="card-number" type="text" id="card-number"/>
      </label>
    </div>
    <div>
      <label>
        <span>Card expiration</span>
        <input data-brick="card-expiration-month" type="text" size="2" id="card-exp-month"/> /
        <input data-brick="card-expiration-year" type="text" size="4" id="card-exp-year"/>
      </label>
    </div>
    <div>
      <label>
        <span>Card CVV</span>
        <input data-brick="card-cvv" type="text" id="card-cvv"/>
      </label>
    </div>
    <button type="submit">Submit</button>
  </form>
  <script>
    var $form = $('#brick-creditcard-form');
    var brick = new Brick({
      public_key: '${public_key}', // please update it to Brick live key before launch your project
      form: { formatter: true }
    }, 'custom');

    $form.submit(function(e) {
      e.preventDefault();

      $form.find('button').prop('disabled', true); // prevent repeat click

      brick.tokenizeCard({ // Tokenize payment details
        card_number: $('#card-number').val(),
        card_expiration_month: $('#card-exp-month').val(),
        card_expiration_year: $('#card-exp-year').val(),
        card_cvv: $('#card-cvv').val()
      }, function(response) {
        console.log(response);
        if (response.type === 'Error') { // faile to create token
          // handle errors
        } else { // token created successfully
          $form.append($('<input type="hidden" name="brick_token"/>').val(response.token));
          $form.append($('<input type="hidden" name="brick_fingerprint"/>').val(Brick.getFingerprint()));
          $form.get(0).submit(); // submit token and fingerprint to your server
        }
      });

      return false;
    });
  </script>
</body>
</html>