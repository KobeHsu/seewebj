(function($) {
    FormValidation.Validator.mobilePhoneNumber = {
        validate: function(validator, $field, options) {
            var value = $field.val();
            if (value === '') {
                return true;
            }

            if (value.length < 10) {
                return false;
            }
            			
            var regex = /09[0-9]{2}-?[0-9]{3}-?[0-9]{3}$/;
            if (!value.match(regex)) {
                return false;
            }

            return true;
        }
    };
}(window.jQuery));