import { useEffect, useState } from "react"


const useForm = (formData) => {
    const [data, setData] = useState(formData);

    useEffect(() => {
        formData && setData(formData);
    }, [formData]);

    const validate = (key) => {
        if(key){
            let hasError = false;
            
            const error = getError(data[key], key);
            error && (hasError = true);

            setData((data) => {
                return {
                    ...data,
                    [key]: {
                        ...data[key],
                        error
                    },
                };
            });
            return hasError;
        }
        else {
            let hasError = false;
            for (let key in data){

                const error = getError(data[key], data);
                error && (hasError = true);

                setData((data) => {
                    return {
                        ...data,
                        [key]: {
                            ...data[key],
                            error
                        },
                    };
                });
            }
            return hasError;
        }
    };

    const setValue = ({ field, value }) => {

        setData((data) => ({
            ...data,
            [field]: {
                ...data[field],
                value,
            },
        }));
    };

    const reset = () => setData(formData);

    return {
        formData: data,
        setValue,
        validate,
        reset,
    };

};

export default useForm;

function getError(field, data){
    switch(true){
        case typeof field.required?.message === 'string' && !field.value:
            return field.required.message;
        case field.required && !field.value:
            return "required field";
        case typeof field.validator === 'function':
            return field.validator(data);
        case field.pattern?.value && !field.pattern.value.test(field.value):
            return field.pattern.message;
        default:
            return undefined;
    }
}