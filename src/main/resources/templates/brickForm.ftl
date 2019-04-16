<!DOCTYPE html>
<html lang="en">
  <script type="text/javascript" src="https://api.paymentwall.com/brick/build/brick-default.1.5.0.min.js"></script>
<body>
  <div id="payment-form-container"></div>
  <script>
    var brick = new Brick({
      public_key: '${public_key}', // please update it to Brick live key before launch your project
      amount: 9.99,
      currency: 'USD',
      container: 'payment-form-container',
      action: '/brick-action',
      form: {
        merchant: 'Paymentwall',
        product: 'Gold Membership',
        pay_button: 'Pay',
        show_zip: true, // show zip code
        show_cardholder: true // show card holder name
      }
    });

    brick.showPaymentForm(function (data) {
      // handle success
      console.log(data);
    }, function (errors) {
      // handle errors
      console.log(errors);
    });
  </script>
</body>
</html>