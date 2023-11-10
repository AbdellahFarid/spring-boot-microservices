import axios from '../myAxios';
import { useState } from 'react';

export const GET = 'get';
export const POST = 'post';
export const PUT = 'put';
export const PATCH = 'patch';
export const DELETE = 'delete';

const useAxios = (method, path) => {
    const [response, setResponse] = useState();
    const [error, setError] = useState();
    const [performing, setPerforming] = useState();


    const call = async ({source, params , body, path: newPath, moreOptions} = {}) => {
        try{
            const _path = newPath || path;
            setPerforming(true);
            let response = await _call(_path, params, body, method, source, moreOptions);
            setResponse(response);
            return response;
        }
        catch(err){
            setError(err);
        } finally {
            setPerforming(false);
        }
    }

    return {
        data: response?.data?.data,
        response,
        error,
        call,
        performing,
    }

    async function _call(path, params, body, method, source, options){

        switch(true){
            case [POST, PATCH, PUT].includes(method):
                return axios[method](path, body, {
                    params,
                    ...options,
                });

            case [GET, DELETE].includes(method):
                return axios[method](path, {
                    data: body,
                    params,
                    ...options
                });
            default:
                throw new Error(`method ${method} is not supported`);
        }
    }
}
export default useAxios;