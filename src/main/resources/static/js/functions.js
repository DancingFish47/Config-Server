async function getCurrentConfig(){

  let call = await fetch('/getCurrentConfig', {
       method: 'POST',
       headers: {
           'Content-Type': 'application/json',
           'Accept': 'application/json'
       }
   });

   let result = await call.json();

   if(result.error){
     document.getElementById("configDiv").innerHTML = result.message;
   } else {
     document.getElementById("configDiv").innerHTML = result.config.id + ' ' + result.config.name + ' ' + result.config.version;
   }
}

async function getNewDbConfig(){
  let call = await fetch('/getNewDbConfig', {
       method: 'POST',
       headers: {
           'Content-Type': 'application/json',
           'Accept': 'application/json'
       }
   });
   let result = await call.json();

   if(result.error){
     document.getElementById("configDiv").innerHTML = result.message;
   } else {
     document.getElementById("configDiv").innerHTML = result.config.id + ' ' + result.config.name + ' ' + result.config.version;
   }
}

async function getNewGitConfig(){
  alert("New Git Config");
}
