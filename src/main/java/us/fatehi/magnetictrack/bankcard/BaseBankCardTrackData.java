/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2016, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */
package us.fatehi.magnetictrack.bankcard;


import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.ServiceCode;

/**
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks">Wikipedia
 *      - ISO/IEC 7813</a>
 */
abstract class BaseBankCardTrackData
  extends BaseTrackData
{

  private static final long serialVersionUID = 7821463290736676016L;

  private final AccountNumber pan;
  private final ExpirationDate expirationDate;
  private final ServiceCode serviceCode;

  BaseBankCardTrackData(final String rawTrackData,
                        final AccountNumber pan,
                        final ExpirationDate expirationDate,
                        final ServiceCode serviceCode,
                        final String discretionaryData)
  {
    super(rawTrackData, discretionaryData);
    this.pan = pan;
    this.expirationDate = expirationDate;
    this.serviceCode = serviceCode;
  }

  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (!(obj instanceof BaseBankCardTrackData))
    {
      return false;
    }
    final BaseBankCardTrackData other = (BaseBankCardTrackData) obj;
    if (expirationDate == null)
    {
      if (other.expirationDate != null)
      {
        return false;
      }
    }
    else if (!expirationDate.equals(other.expirationDate))
    {
      return false;
    }
    if (pan == null)
    {
      if (other.pan != null)
      {
        return false;
      }
    }
    else if (!pan.equals(other.pan))
    {
      return false;
    }
    if (serviceCode == null)
    {
      if (other.serviceCode != null)
      {
        return false;
      }
    }
    else if (!serviceCode.equals(other.serviceCode))
    {
      return false;
    }
    return true;
  }

  /**
   * Gets the primary account number for the card.
   *
   * @return Primary account number.
   */
  public AccountNumber getAccountNumber()
  {
    return pan;
  }

  /**
   * Gets the primary account number for the card.
   *
   * @return Primary account number.
   */
  public ExpirationDate getExpirationDate()
  {
    return expirationDate;
  }

  /**
   * Gets the card service code.
   *
   * @return Card service code.
   */
  public ServiceCode getServiceCode()
  {
    return serviceCode;
  }

  /**
   * Checks whether the primary account number for the card is
   * available.
   *
   * @return True if the primary account number for the card is
   *         available.
   */
  public boolean hasAccountNumber()
  {
    return pan != null && pan.hasAccountNumber();
  }

  /**
   * Checks whether the card expiration date is available.
   *
   * @return True if the card expiration date is available.
   */
  public boolean hasExpirationDate()
  {
    return expirationDate != null && expirationDate.hasExpirationDate();
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result
             + (expirationDate == null? 0: expirationDate.hashCode());
    result = prime * result + (pan == null? 0: pan.hashCode());
    result = prime * result + (serviceCode == null? 0: serviceCode.hashCode());
    return result;
  }

  /**
   * Checks whether the card service code is available.
   *
   * @return True if the card service code is available.
   */
  public boolean hasServiceCode()
  {
    return serviceCode != null && serviceCode.hasServiceCode();
  }

  /**
   * Verifies that the available data is consistent between Track 1 and
   * Track 2, or any other track.
   *
   * @return True if the data is consistent.
   */
  public boolean isConsistentWith(final BaseBankCardTrackData other)
  {
    if (this == other)
    {
      return true;
    }
    if (other == null)
    {
      return false;
    }
    boolean equals = true;

    if (hasAccountNumber() && other.hasAccountNumber())
    {
      if (!getAccountNumber().equals(other.getAccountNumber()))
      {
        equals = false;
      }
    }

    if (hasExpirationDate() && other.hasExpirationDate())
    {
      if (!getExpirationDate().equals(other.getExpirationDate()))
      {
        equals = false;
      }
    }

    if (hasServiceCode() && other.hasServiceCode())
    {
      if (!getServiceCode().equals(other.getServiceCode()))
      {
        equals = false;
      }
    }

    return equals;
  }

}
