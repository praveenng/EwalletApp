import axios from 'axios';

export function getDataSession(config, callback, errorcallback){
    axios.get('/payment/getUserSessionValues', config)
    .then(res => {
      //do something
      if(callback != null){
         callback(res);
      }
    })
    .catch(err => {
      // catch error
      if(errorcallback != null){
         errorcallback(err);
      }
    })
}