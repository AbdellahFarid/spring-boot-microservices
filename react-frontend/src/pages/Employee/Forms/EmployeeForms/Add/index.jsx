import { Button, MenuItem, Select } from '@mui/material';
import { Divider, TextField, Typography } from '@mui/material'
import React from 'react'
import inputPropType from '../../../../../utils/components/inputPropType';
import PropTypes from 'prop-types';
import CustomSelect from '../../../../../utils/custom-ui-kit/CustomSelect/CustomSelect';

const EmployeeForm = ({
  firstName,
  lastName,
  email,
  setInputValue,
  onSubmit,
  onReset,
  canSubmit,
}) => {
  return (
    <>
      <div >
        <Typography variant='h5'>General Infos</Typography>
        <Divider/>
      </div>
      <div>
        <TextField
          size='small'
          error={!!firstName.error}
          helperText={firstName.error}
          disabled={firstName.disabled}
          value={firstName.value}
          label={"First name"}
          onChange={(e) => setInputValue({ field: "firstName", value: e.target.value})}
        />

        <TextField
          size='small'
          error={!!lastName.error}
          helperText={lastName.error}
          disabled={lastName.disabled}
          value={lastName.value}
          label={"Last name"}
          onChange={(e) => setInputValue({ field: "lastName", value: e.target.value})}
        />

        <TextField
          size='small'
          error={!!email.error}
          helperText={email.error}
          disabled={email.disabled}
          value={email.value}
          label={"Email (Optional)"}
          onChange={(e) => setInputValue({ field: "email", value: e.target.value})}
        />

        <CustomSelect/>
      </div>
      <div>
        <Button disabled={!canSubmit} onClick={onSubmit} color='primary' variant="outlined">Confirm</Button>
        <Button disabled={!canSubmit} onClick={onReset} color='warning' variant="outlined">Reset</Button>
      </div>
      <CustomSelect/>
    </>
  )
}

EmployeeForm.propTypes = {
  firstName: inputPropType,
  lastName: inputPropType,
  email: inputPropType,
  setInputValue: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired,
  onReset: PropTypes.func,
  canSubmit: PropTypes.bool,


}

export default EmployeeForm