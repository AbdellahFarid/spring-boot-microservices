
const dataInputsFormatter = () => {
    return {
        firstName: {
            value: '',
            required: true,
        },
        
        lastName: {
            value: '',
            required: true,
        },

        email: {
            value: '',
            required: true
        },
        dapartmentCode: {
            value: '',
            required: true
        }
    };
};

export default dataInputsFormatter;