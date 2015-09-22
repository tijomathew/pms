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
                labelClass: "col-sm-1 col-sm-2",
                labelClassReplace: "col-sm-4",
                parentElementType: "div",
                parentElementClass: "col-sm-1 col-sm-2 col-sm-3 col-sm-4",
                parentElementClassReplace: "col-sm-7",
                radioButtonGroupClass: "btn-group",
                responseData: {},
                tabHeaderEltSelector : "div.tab-pane",
                parentFieldSet: $([]),
                groupFieldSet: $([]),
                propKey: "fieldName",
                dialog:"",
                colSpan: 1
            }, options),
            customDialog =  function() {

                $('<div/>', {id: "errorFieldsDialog"}).dialog({
                    width: 500,
                    //height: 300,

                    //position: ['center', 50] ,
                    //autoOpen: false,
                    show: "fold",
                    hide: "fold",
                    //autoOpen:false,
                    modal: true,
                    resizable: false,
                    closeText: "Close",
                    draggable: true,
                    open: function () {
                        $defaults.dialog = $(this);
                        $(this).html(cloneElementsToDialog);
                        var $dateFields = $(this).find('input.date');
                        if($dateFields.length) {
                          //  $dateFields.datepicker("destroy");
                            $dateFields.datepicker({
                                autoclose: true,
                                todayHighlight: true,
                                format: 'dd-mm-yyyy',
                                endDate: '+0d',
                                onClose: function() {$(this).valid();}
                            });
                        }
                    },
                    title: 'Please Update Values',

                    buttons: {

                        "update": {
                            text: "Update Values",
                            class: "btn btn-sm btn-primary",
                            click:  function() {
                                updateFormValues();
                                showProcessing();
                                setTimeout(function () {
                                    $defaults.dialog.dialog( "close" );
                                    globalSubmissionOfForms($defaults.parentFormId, $defaults.parentGridId);
                                }, 1000);
                                //showProcessing;
                                //$( this ).dialog( "close" );
                            }
                        },
                        "cancel": {
                            text : 'Cancel',
                            class: 'btn btn-sm btn-primary',
                            click: function() {
                                $( this ).dialog( "close" );
                            }
                        }

                    }
                });
            },
            createParentElement = function() {
                return $("<div/>", {class: "form-group"});
            },
            createParentFieldSetElement = function($elt) {
                var fieldSetLabel = $defaults.parentFieldSet ? $defaults.parentFieldSet.find('legend').text() : "";
                var eltFieldSetLabel = $("li a[href='#" + $elt.parents($defaults.tabHeaderEltSelector).attr("id") +"']").text(), fieldSet;
                if(fieldSetLabel != eltFieldSetLabel) {
                    $defaults.parentFieldSet = $("<fieldset/>", {class : "scheduler-border"}).append($("<legend/>", {class : "scheduler-border", text: eltFieldSetLabel}));
                }

                return $defaults.parentFieldSet;
            },
            createGroupFieldSetElement = function($elt) {
                var $groupFieldSetLabel = $defaults.groupFieldSet ? $defaults.groupFieldSet.find('legend').text() : "", $eltFieldSet =  $elt.parents("fieldset");

                if($eltFieldSet != $defaults.parentFieldSet) {
                    if($groupFieldSetLabel != $eltFieldSet.find('legend').text()) {
                        $defaults.groupFieldSet = $("<fieldset/>", {class : "scheduler-border"}).append($("<legend/>", {class : "scheduler-border", text: $eltFieldSet.find('legend').text(), style: "font-size:12px;"}));
                    }
                } else {
                    $defaults.groupFieldSet = $([]); // reset field set
                }


                return $defaults.groupFieldSet;
            },
            reOrderNames = function(data) {
                var eltNames = new Array(),  $form = $("form#" + $defaults.parentFormId), $formElts = $("form#" + $defaults.parentFormId).find(':input');

                return data.sort(function(a, b) {
                    var elm1 = $form.find("[name=" + a[$defaults.propKey].replace(/\./g, "\\.") + "]"), index1 = $formElts.index(elm1);
                    var elm2 = $form.find("[name=" + b[$defaults.propKey].replace(/\./g, "\\.") + "]"), index2 = $formElts.index(elm2);
                    return (index1 == index2) ? 0 : (index1 > index2) ? 1 : -1;
                });
            },
            cloneElementsToDialog = function() {
                var $elt, $label, $form = $("form#" + $defaults.parentFormId ), $rootElement = $("<div/>", {id: "eltsContainer", class: "form-horizontal", style: "height:400px;margin-left:15px;overflow-x:hidden;z-index:9999;"}), $parentBlock,  $fieldSet, $groupFieldSet;
                var $parentFSLbl, $groupFSLbl, $currentParentFSLbl, $currentGroupFSLbl;
                $.each(reOrderNames($defaults.responseData), function(index, item) {
                    if(!$rootElement.find("[name=" + item[$defaults.propKey].replace(/\./g, "\\.") + "]").length) {
                        $elt                = $form.find("[name=" + item[$defaults.propKey].replace(/\./g, "\\.") + "]");
                        $parentFSLbl        = $fieldSet ? $fieldSet.find('legend:eq(0)').text() : "";
                        $groupFSLbl         = $groupFieldSet ? $groupFieldSet.find('legend').text() : "";
                        $currentParentFSLbl = createParentFieldSetElement($elt).length ? $defaults.parentFieldSet.find('legend').text() : "";
                        $currentGroupFSLbl  = createGroupFieldSetElement($elt).length ? $defaults.groupFieldSet.find('legend').text() : "";

                        if($parentFSLbl !== $currentParentFSLbl) {
                            $fieldSet = $defaults.parentFieldSet;
                            $rootElement.append($fieldSet);
                            //$defaults.parentFieldSet = $fieldSet;
                        } else if($groupFSLbl !== $currentGroupFSLbl) {
                            $groupFieldSet = $defaults.groupFieldSet;
                            $fieldSet.append($groupFieldSet);
                        }

                        if(index % $defaults.colSpan === 0) {
                            $parentBlock = createParentElement();
                            if($groupFSLbl)
                                $groupFieldSet.append($parentBlock);
                            else
                                $fieldSet.append($parentBlock);
                        }

                        $label =  $form.find("label[for=" + item[$defaults.propKey].replace(/\./g, "\\.") + "]");
                        if(!$label.length && $elt.attr("id"))
                            $label =  $form.find("label[for=" + $elt.attr("id").replace(/\./g, "\\.") + "]");
                        $parentBlock.append($label.clone().removeClass($defaults.labelClass).addClass($defaults.labelClassReplace)).append( $elt.closest( "div" ).clone().removeClass($defaults.parentElementClass).addClass($defaults.parentElementClassReplace));
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

                var $type = $(this).attr("type"), $value = $(this).val(), $id = $(this).attr("id"), $elt = ($id ? $form.find(":input[id=" + $id.replace(/\./g, "\\.")  +"]") : ""),
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
                                    $elt.find("option[value='" + values[i] +"']").prop("selected", true);
                                }
                            }
                            break;

                        case 'select':
                        case 'select-one':
                            if ($(this).hasClass('toCaps')) {
                                $value = $value.replace(/\s/g, '').toUpperCase();
                            }

                            var values = $value.constructor == Array ? $value : [$value];
                            for (var i = 0; i < $elt.find("option").length; i++) {
                                for (var j = 0; j < values.length; j++) {
                                    var solveDotIssueInValue = values[j].replace(/\./g, '\\.');
                                    if ($(this).hasClass('toaddUnderScore')) {
                                        $value = solveDotIssueInValue.replace(' ', '_').toUpperCase();
                                        solveDotIssueInValue = $value.replace(/\_/g, '\\_');
                                    }
                                    $elt.find('option[value="' + solveDotIssueInValue + '"]').prop('selected', true);
                                }
                            }

                            break;
                        case 'text':
                        case 'hidden':
                            $elt.val($value);
                            break;

                        case 'textarea':
                            //$elt.html($value);
                            $elt.val($value);
                            break;
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