import React from 'react'
import dataInputsFormatter from '../Forms/EmployeeForms/Add/dataInputsFormatter';
import useForm from '../../../hooks/useForm';
import useAxios, {POST} from '../../../hooks/useAxios';
import { EMPLOYEES_PATH } from '../../../myAxios'
import EmployeeForm from '../Forms/EmployeeForms/Add';
import axios from 'axios';
import { Card } from '@mui/material';

const initialFormValue = dataInputsFormatter();

const PostEmployee = () => {

    const { call, performing } = useAxios(POST, EMPLOYEES_PATH);
    const { formData, setValue, validate, reset } = useForm(initialFormValue);

    const onSubmit = () => {
        if(validate() || performing){
            return;
        }
        const source = axios.CancelToken;
        const keys = Object.keys(formData);
        const requestBody = {};
        console.log(keys);
        console.log(formData);
        keys.forEach((key) => {
            requestBody[key] = formData[key].value;
        })

        console.log(requestBody);
        call({ body: requestBody, source}).then((response) => {
            console.log(response);
        })
        return source.cancel;
    }

  return (
    <Card>
    <EmployeeForm 
        {...formData}
        setInputValue={setValue}
        onSubmit={onSubmit}
        onReset={reset}
        canSubmit={!performing}
    />
    </Card>
  )
}

export default PostEmployee;