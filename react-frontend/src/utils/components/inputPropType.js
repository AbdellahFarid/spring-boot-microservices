import PropTypes from 'prop-types'

const inputPropType = PropTypes.shape({
    value: PropTypes.any,
    error: PropTypes.string,
    disabled: PropTypes.bool
}).isRequired;

export default inputPropType;