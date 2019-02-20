import _isNumber from 'lodash/isNumber';
import _toString from 'lodash/toString';

export const decimalFormatter = value => {
  if (_isNumber(value)) {
    value = _toString(value);
  }

  value = value
    .replace(/[A-Za-z]/g, '')
    .replace(/[^\dM-]/g, '')
    .replace(/^-/, 'N')
    .replace(/-/g, '')
    .replace(/^(-)?0+(?=\d)/, '$1');

  let partInteger = value;
  partInteger = partInteger.replace(/(\d)(?=(\d{3})+$)/g, '$1' + '.');

  return partInteger.toString();
};

export const getValueMoney = (number, decimalPrecision = 2) => {
  let s = typeof number === 'number' ? number.toFixed(decimalPrecision).toString() : null;

  if (s) {
    s = s.replace(/\./g, '').replace(/,/g, '');

    // add comma to the decimal
    if (decimalPrecision > 0) {
      s = s.slice(0, s.length - decimalPrecision) + ',' + s.slice(s.length - decimalPrecision, s.length);
    }

    // add dot in thousand, ignoring number after the comma
    let match = s.match(/,/);
    return (
      match.input.substring(0, match.index).replace(/\B(?=(\d{3})+(?!\d))/g, '.') +
      match.input.substring(match.index, match.input.length)
    );
  }

  return '-';
};
