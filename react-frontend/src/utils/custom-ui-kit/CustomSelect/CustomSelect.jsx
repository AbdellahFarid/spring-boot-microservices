import { FormControl, InputLabel, MenuItem, Select } from '@mui/material'
import React, { useEffect, useState } from 'react'
import useAxios, { GET } from '../../../hooks/useAxios'
import { DEPARTMENTS_PATH } from '../../../myAxios'
import { makeStyles } from '@mui/styles'

const useStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(2),
    minWidth: 120
  }
}))


const CustomSelect = (url, onSelect) => {
  const classes = useStyles();

  const {data: departments, call: fetchDepartments} = useAxios(GET, DEPARTMENTS_PATH);
  const [selectedDepartment, setSelectedDepartment] = useState('');

  useEffect(() => {
    fetchDepartments()},
    []
  );

  const handleOnChange = (event) => {

    setSelectedDepartment(event.target.value);
  };

  return (
    <>
      <FormControl className={classes.formControl}>
        <InputLabel id='department-select-label'>Department</InputLabel>
        <Select>
          {departments?.map((department) => (
            <MenuItem key={department.id} value={department.id}>
              {department.departmentName}
            </MenuItem>
          ))}
        </Select>

      </FormControl>
    </>
  )
}

export default CustomSelect