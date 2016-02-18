/**
 * formControl.js --> for maintaining data decorum in User Registration 
 */
function addressCopy(f){
 if(f.sameAddress.checked == true) {
            f.cAddress.value = f.pAddress.value;
            f.cAddress.disabled = true;
            f.cstate.value = f.pstate.value;
            f.cstate.disabled = true;
            f.ccountry.value = f.pcountry.value;
            f.ccountry.disabled = true;
            //document.write(f.pstate.value);
           
            f.ccity.value = f.pcity.value;
            f.ccity.disabled = true;
            f.czip.value = f.pzip.value;
            f.czip.disabled = true;
        }
 else{
            f.cAddress.value = '';
            f.cAddress.disabled = false;
            f.ccountry.value = '';
            f.ccountry.disabled = false;
            f.cstate.value = '';
            f.cstate.disabled = false;
            f.ccity.value = '';
            f.ccity.disabled = false;
            f.czip.value = '';
            f.czip.disabled = false; 
       }
    }




