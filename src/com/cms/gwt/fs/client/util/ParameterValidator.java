package com.cms.gwt.fs.client.util;

/**
 * Methods in this class perform parameter validation and will through IllegalArgumentException
 * when the validation fails.
 * <p/>
 * Extend a base Validator class and implement the logging?
 */
public class ParameterValidator
{
    /**
     * Validate String parameter.
     *
     * @param paramName
     * @param paramValue
     * @throws IllegalArgumentException This method will throw IllegalArgumentExcepion when the value is empty or null.
     */
    public static void validateStringParam(String paramName, String paramValue)
    {
        if (paramValue == null || "".equals(paramValue.trim()))
        {
            throw new IllegalArgumentException("Parameter " + paramName + " cannot be null or empty.");
        }
    }

    /**
     * Validate parameter.
     *
     * @param paramName  The parameter name.
     * @param paramValue The parameter value.
     * @throws IllegalArgumentException This method will throw IllegalArgumentException when the value is null.
     */
    public static void validateParam(String paramName, Object paramValue)
    {
        if (paramValue == null)
        {
            throw new IllegalArgumentException("Parameter " + paramName + " cannot be null.");
        }
    }
}