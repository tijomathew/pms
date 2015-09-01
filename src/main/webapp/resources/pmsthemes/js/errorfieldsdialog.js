/**
 * Created by tijo on 22/8/15.
 */


(function( $ ) {

    $.fn.errorFieldsDialog = function(options) {

        // defaults options.
        var $defaults = $.extend({
            // These are the defaults.
            parentFormId:"",
            parentGridId:"",
            errorFieldClass: "borderRed",
            errorLabelClass: "labelErrorAlert",
            parentElementType: "div",
            parentElementClass: "col-sm-1 col-sm-2 col-sm-3 col-sm-4",
            parentElementClassReplace: "col-sm-6",
            parentLabelClass: "col-sm-1 col-sm-2",
            parentLabelClassReplace: "col-sm-3",
            radioButtonGroupClass: "btn-group",
            responseData: {},
            propKey: "fieldName",
            dialog:"",
            colSpan: 1
        }, options),
         customDialog =  function() {

             $('<div/>', {id: "errorFieldsDialog"}).dialog({
                width: 350,
                //height: 300,

               // position: ['center', 50] ,
                //autoOpen: false,
                show: "blind",
                hide: "explode",
                //autoOpen:false,
                modal: true,
                resizable: false,
                draggable: false,
                open: function () {
                    $defaults.dialog = $(this);
                    $(this).html(cloneElementsToDialog);
                },
                title: 'Please Update Values',
                buttons: {
                    "Update Values": function() {
                        updateFormValues();
                        showProcessing();
                        setTimeout(function () {
                            $defaults.dialog.dialog( "close" );
                            globalSubmissionOfForms($defaults.parentFormId, $defaults.parentGridId);
                        }, 1000);
                        //showProcessing;
                        //$( this ).dialog( "close" );
                    },
                    Cancel: function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
        },
        createParentElement = function() {

            return $("<div/>", {class: "form-group"});
        },
        cloneElementsToDialog = function() {
            var $elt, $label, $form = $("form#" + $defaults.parentFormId ), $rootElement = $("<div/>", {id: "eltsContainer", class: "form-horizontal", style: "height:300px;overflow-x:hidden;z-index:5;"}), $parentBlock;

            $.each($defaults.responseData, function(index, item) {
                if(!$rootElement.find("[name=" + item[$defaults.propKey].replace(/\./g, "\\.") + "]").length) {
                    if(index % $defaults.colSpan === 0) {
                        $parentBlock = createParentElement();
                        $rootElement.append($parentBlock);
                    }
                    $elt = $form.find("[name=" + item[$defaults.propKey].replace(/\./g, "\\.") + "]");
                    $label =  $form.find("label[for=" + item[$defaults.propKey].replace(/\./g, "\\.") + "]");
                    if(!$label.length && $elt.attr("id"))
                        $label =  $form.find("label[for=" + $elt.attr("id").replace(/\./g, "\\.") + "]");
                    $parentBlock.append($label.clone().removeClass($defaults.parentLabelClass).addClass($defaults.parentLabelClassReplace)).append( $elt.closest( "div" ).clone().removeClass($defaults.parentElementClass).addClass($defaults.parentElementClassReplace));
                    //$parentBlock.append($label.clone()).append( $elt.closest( "div" ).clone().switchClass($defaults.parentElementClass, $defaults.parentElementClassReplace));
                    //$parentBlock.append($label).append( $elt.parents( "div." + $defaults.parentElementClass).clone());

                    //this.dialog.html().append($parentBlock.clone());
                }

            });
            return $rootElement;
        },
        clearElementErrors = function($elt) {
            $elt.removeAttr('data-original-title').removeClass($defaults.errorFieldClass);
            var $label =  $("label[for=" + $elt.attr('name').replace(/\./g, "\\.") + "]");
            if(!$label.length && $elt.attr("id"))
                $label =  $("label[for=" + $elt.attr("id").replace(/\./g, "\\.") + "]");
            if($label.length)
                $label.removeClass($defaults.errorLabelClass);
        }
        updateFormValues = function() {
            var $elts = $defaults.dialog.find(":input"), $form = $("form#" + $defaults.parentFormId), $parentBlock;

            $($elts).each(function(index, element) {

                var $type = $(this).attr("type"), $value = $(this).val(), $id = $(this).attr("id"), $elt = $form.find("input[id=" + $id.replace(/\./g, "\\.")  +"]"),
                    $isValid = (($type === 'radio' || $type === 'checkbox') && !$(this).is(':checked')) ?  false : ($value ? true : false);
                if (!$type)
                    $type = element.tagName.toLowerCase();

               if($isValid) {
                   switch ($type) {

                       case 'radio':
                           if($(this).is(":checked")) {
                               if ($(this).hasClass('toCaps')) {
                                   $value = $value.replace(/\s/g, '').toUpperCase();
                               }
                               $elt.prop("checked", "true");
                               $elt.parent("label").addClass('active');
                           }
                           break;

                       case 'checkbox':
                           if($(this).is(":checked")) {
                               $elt.prop("checked", "true");
                           }
                           break;

                       case 'select-multiple':
                           var values = $value.constructor == Array ? $value : [$value];
                           for (var i = 0; i < $elt.find("option").length; i++) {
                               for (var j = 0; j < values.length; j++) {
                                   $elt.find("option[value='" + values[i] +"']").attr("selected", true);
                               }
                           }
                           break;

                       case 'select':
                       case 'select-one':
                           if ($(this).hasClass('toCaps')) {
                               $value = $value.replace(/\s/g, '').toUpperCase();
                           }
                           var solveDotIssueInValue = $value.replace(/\./g, '\\.');
                           if ($(this).hasClass('toaddUnderScore')) {
                               $value = $value.replace(' ', '_').toUpperCase();
                               $value = $value.replace(' ', '_').toUpperCase();
                               solveDotIssueInValue = $value.replace(/\_/g, '\\_');
                           }
                           $elt.find('option[value="' + solveDotIssueInValue + '"]').prop('selected', true);//.end().attr("disabled", true);
                           break;
                       case 'text':
                       case 'hidden':
                           $elt.val($value);
                           break;

                       case 'textarea':
                           $elt.html($value);
                       case 'submit':
                       case 'button':
                       default:
                           try {
                               $elt.html($value);
                               // $updated = true;
                           } catch (exc) {
                           }
                   }

                   clearElementErrors($elt);
               }

            });

        },
        closeDialog = function() {

        },
        showProcessing = function() {
            $defaults.dialog.html('<i class="fa fa-refresh fa-spin dialog-spinner"></i>');
        };
        //return $defaults.dialog;
       customDialog();

    };

}( jQuery ));